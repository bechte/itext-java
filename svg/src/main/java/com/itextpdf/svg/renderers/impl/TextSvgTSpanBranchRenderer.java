/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2024 Apryse Group NV
    Authors: Apryse Software.

    This program is offered under a commercial and under the AGPL license.
    For commercial licensing, contact us at https://itextpdf.com/sales.  For AGPL licensing, see below.

    AGPL licensing:
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.itextpdf.svg.renderers.impl;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.element.Text;
import com.itextpdf.svg.renderers.ISvgNodeRenderer;
import com.itextpdf.svg.renderers.SvgDrawContext;

public class TextSvgTSpanBranchRenderer extends TextSvgBranchRenderer {

    public TextSvgTSpanBranchRenderer() {
        this.performRootTransformations = false;
    }

    @Override
    public Rectangle getObjectBoundingBox(SvgDrawContext context) {
        return getParent().getObjectBoundingBox(context);
    }

    @Override
    public ISvgNodeRenderer createDeepCopy() {
        TextSvgBranchRenderer copy = new TextSvgTSpanBranchRenderer();
        fillCopy(copy);
        return copy;
    }

    @Override
    protected void doDraw(SvgDrawContext context) {
        if (getChildren().isEmpty() || this.attributesAndStyles == null) {
            return;
        }
        for (ISvgTextNodeRenderer child : getChildren()) {
            resolveFont(context);
            if (child instanceof TextLeafSvgNodeRenderer) {
                Text text = ((TextLeafSvgNodeRenderer) child).getText();
                applyFontProperties(text, context);
                applyTextRenderingMode(text);
            }
            processChild(context, child);
        }
    }
}
