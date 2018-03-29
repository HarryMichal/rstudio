/*
 * ShinyViewerOptions.java
 *
 * Copyright (C) 2009-18 by RStudio, Inc.
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
package org.rstudio.studio.client.shiny.model;

import com.google.gwt.core.client.JavaScriptObject;

public class ShinyViewerOptions extends JavaScriptObject
{
   protected ShinyViewerOptions() {}
   
   public final static int SHINY_VIEWER_OPTIONS_NONE = 0;
   public final static int SHINY_VIEWER_OPTIONS_NOTOOLS = 1;
   public final static int SHINY_VIEWER_OPTIONS_WIDE = 2;
}