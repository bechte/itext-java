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
package com.itextpdf.bouncycastle.asn1.x509;

import com.itextpdf.bouncycastle.asn1.ASN1EncodableBC;
import com.itextpdf.commons.bouncycastle.asn1.x509.IExtension;
import com.itextpdf.commons.bouncycastle.asn1.x509.IExtensions;

import org.bouncycastle.asn1.x509.Extensions;

/**
 * Wrapper class for {@link Extensions}.
 */
public class ExtensionsBC extends ASN1EncodableBC implements IExtensions {
    /**
     * Creates new wrapper instance for {@link Extensions}.
     *
     * @param extensions {@link Extensions} to be wrapped
     */
    public ExtensionsBC(Extensions extensions) {
        super(extensions);
    }

    /**
     * Creates new wrapper instance for {@link Extensions}.
     *
     * @param extensions Extension wrapper
     */
    public ExtensionsBC(IExtension extensions) {
        super(new Extensions(((ExtensionBC) extensions).getExtension()));
    }

    /**
     * Gets actual org.bouncycastle object being wrapped.
     *
     * @return wrapped {@link Extensions}.
     */
    public Extensions getExtensions() {
        return (Extensions) getEncodable();
    }
}
