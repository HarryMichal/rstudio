/*
 * TerminalPreferencesPane.java
 *
 * Copyright (C) 2009-19 by RStudio, Inc.
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
package org.rstudio.studio.client.workbench.prefs.views;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.inject.Inject;
import org.rstudio.core.client.ElementIds;
import org.rstudio.core.client.resources.ImageResource2x;
import org.rstudio.core.client.widget.LabeledTextBox;
import org.rstudio.studio.client.common.GlobalDisplay;
import org.rstudio.studio.client.server.Server;
import org.rstudio.studio.client.workbench.model.Session;
import org.rstudio.studio.client.workbench.prefs.model.UserPrefs;

public class AccessibilityPreferencesPane extends PreferencesPane
{
   @Inject
   public AccessibilityPreferencesPane(UserPrefs prefs,
                                       PreferencesDialogResources res,
                                       Session session,
                                       final GlobalDisplay globalDisplay,
                                       final Server server)
   {
      prefs_ = prefs;
      res_ = res;
      session_ = session;
      server_ = server;

      add(headerLabel("Screen Reader"));
      chkScreenReaderEnabled_ = new CheckBox("Enable screen reader support");
      textStatusDelayText_ = new LabeledTextBox("Speak typing results delay (ms)",
            ElementIds.getElementId(ElementIds.A11Y_PREF_TYPING_DELAY));
      textStatusDelayText_.setLabelInline(true);
      add(chkScreenReaderEnabled_);
      add(textStatusDelayText_);
   }

   @Override
   public ImageResource getIcon()
   {
      return new ImageResource2x(res_.iconAccessibility2x());
   }

   @Override
   public String getName()
   {
      return "Accessibility";
   }

   @Override
   protected void initialize(UserPrefs prefs)
   {

   }

   @Override
   public boolean onApply(UserPrefs rPrefs)
   {
      boolean restartRequired = super.onApply(rPrefs);
      return restartRequired;
   }

   private final CheckBox chkScreenReaderEnabled_;
   private final LabeledTextBox textStatusDelayText_;

      // Injected ----
   private final UserPrefs prefs_;
   private final PreferencesDialogResources res_;
   private final Session session_;
   private final Server server_;
}
