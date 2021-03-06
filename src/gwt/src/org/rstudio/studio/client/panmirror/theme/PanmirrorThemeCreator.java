/*
 * PanmirrorThemeCreator.java
 *
 * Copyright (C) 2009-20 by RStudio, PBC
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */

package org.rstudio.studio.client.panmirror.theme;

import org.rstudio.core.client.BrowseCap;
import org.rstudio.core.client.Debug;
import org.rstudio.core.client.dom.DomUtils;
import org.rstudio.core.client.theme.ThemeFonts;
import org.rstudio.studio.client.workbench.prefs.model.UserPrefs;
import org.rstudio.studio.client.workbench.views.source.DocumentOutlineWidget;
import org.rstudio.studio.client.workbench.views.source.editors.text.themes.AceTheme;

import com.google.gwt.core.client.JsArrayString;

public class PanmirrorThemeCreator
{
   public static PanmirrorTheme themeFromEditorTheme(AceTheme aceTheme, UserPrefs prefs)
   {
      // create theme from current app theme
      PanmirrorTheme theme = new PanmirrorTheme(); 
      
      theme.cursorColor = DomUtils.extractCssValue("ace_cursor", "color");
      
      // having trouble with selection colors in dark mode...the ace_selection color appears
      // to be getting blended to yield the actual color used. for now we will just 
      // use the terminal selection color
      JsArrayString selectionBkgdClasses = JsArrayString.createArray().cast();
      if (aceTheme.isDark())
      {
         selectionBkgdClasses.push("terminal");
         selectionBkgdClasses.push("xterm-selection");
         selectionBkgdClasses.push("fake-class");
      }
      else
      {
         selectionBkgdClasses.push("ace_marker-layer");
         selectionBkgdClasses.push("ace_selection");
      }
      theme.selectionColor = DomUtils.extractCssValue(selectionBkgdClasses, "backgroundColor");
      theme.backgroundColor = DomUtils.extractCssValue("ace_editor", "backgroundColor");
      theme.metadataBackgroundColor = theme.backgroundColor;
      theme.nodeSelectionColor = DomUtils.extractCssValue("ace_node-selector", "backgroundColor");
      theme.commentBackgroundColor = DomUtils.extractCssValue("ace_comment-highlight", "backgroundColor");
         
      JsArrayString regionBkgdClasses = JsArrayString.createArray().cast();
      regionBkgdClasses.push("ace_marker-layer");
      regionBkgdClasses.push("ace_foreign_line");
      String regionBkgdColor = DomUtils.extractCssValue(regionBkgdClasses, "backgroundColor");
      theme.chunkBackgroundColor = regionBkgdColor;
      theme.spanBackgroundColor = regionBkgdColor;
      theme.divBackgroundColor = regionBkgdColor;
      
      theme.textColor =  DomUtils.extractCssValue("ace_editor", "color");
      theme.lightTextColor = DomUtils.extractCssValue("ace_support ace_function", "color");
      theme.linkTextColor = DomUtils.extractCssValue("ace_keyword", "color");
      theme.markupTextColor = DomUtils.extractCssValue("ace_markup ace_list ace_string", "color");
      
      JsArrayString findTextClasses = JsArrayString.createArray().cast();
      findTextClasses.push("ace_marker-layer");
      findTextClasses.push("ace_selected-word");
      theme.findTextBackgroundColor = DomUtils.extractCssValue(findTextClasses, "backgroundColor");
      theme.findTextBorderColor = DomUtils.extractCssValue(findTextClasses, "borderColor");
      
      JsArrayString borderBkgdClasses = JsArrayString.createArray().cast();
      borderBkgdClasses.push("ace_marker-layer");
      borderBkgdClasses.push("ace_find_line");
      String borderColor = DomUtils.extractCssValue(borderBkgdClasses, "backgroundColor");
      theme.borderBackgroundColor = borderColor;
      theme.blockBorderColor = borderColor;
      theme.focusOutlineColor = borderColor;
      
      theme.gutterBackgroundColor = DomUtils.extractCssValue("ace_gutter", "backgroundColor");
      theme.gutterTextColor = DomUtils.extractCssValue("ace_gutter", "color");
    
      
      // pane border based on outline widget border (it's a single color for all themes)
      theme.paneBorderColor =  DomUtils.extractCssValue(DocumentOutlineWidget.RES.styles().leftSeparator(), "borderLeftColor");
      
      // calculate standard font size in pts
      double fontSize = prefs.fontSizePoints().getValue();
      fontSize = fontSize + BrowseCap.getFontSkew();
      theme.fixedWidthFont = ThemeFonts.getFixedWidthFont();
      theme.fixedWidthFontSizePt = fontSize;
      theme.proportionalFont = ThemeFonts.getProportionalFont();
      theme.proportionalFontSizePt = fontSize + 1;
      
      PanmirrorThemeCode code = new PanmirrorThemeCode();
      code.keywordColor = DomUtils.extractCssValue("ace_keyword", "color");
      code.atomColor = DomUtils.extractCssValue("ace_constant ace_language", "color");
      code.numberColor = DomUtils.extractCssValue("ace_constant ace_numeric", "color");
      code.variableColor = theme.textColor;
      code.defColor = theme.textColor;
      code.operatorColor = DomUtils.extractCssValue("ace_keyword ace_operator", "color");
      code.commentColor =  DomUtils.extractCssValue("ace_comment", "color");
      code.stringColor = DomUtils.extractCssValue("ace_string", "color");
      code.metaColor = theme.textColor;
      code.builtinColor = theme.textColor;
      code.bracketColor = code.operatorColor;
      code.tagColor = DomUtils.extractCssValue("ace_meta ace_tag", "color");
      code.attributeColor = theme.textColor;
      code.hrColor = theme.textColor;
      code.linkColor = theme.linkTextColor;
      code.errorColor = DomUtils.extractCssValue(AceTheme.getThemeErrorClass(aceTheme), "color"); 
      theme.code = code;
      
      return theme;
   }
   
  
   
}
