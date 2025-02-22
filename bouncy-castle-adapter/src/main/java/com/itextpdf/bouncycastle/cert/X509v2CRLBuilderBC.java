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
package com.itextpdf.bouncycastle.cert;

import com.itextpdf.bouncycastle.asn1.ASN1EncodableBC;
import com.itextpdf.bouncycastle.asn1.ASN1ObjectIdentifierBC;
import com.itextpdf.bouncycastle.asn1.x500.X500NameBC;
import com.itextpdf.bouncycastle.operator.ContentSignerBC;
import com.itextpdf.commons.bouncycastle.asn1.IASN1Encodable;
import com.itextpdf.commons.bouncycastle.asn1.IASN1ObjectIdentifier;
import com.itextpdf.commons.bouncycastle.asn1.x500.IX500Name;
import com.itextpdf.commons.bouncycastle.cert.IX509CRLHolder;
import com.itextpdf.commons.bouncycastle.cert.IX509v2CRLBuilder;
import com.itextpdf.commons.bouncycastle.operator.IContentSigner;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

import org.bouncycastle.cert.X509v2CRLBuilder;

/**
 * Wrapper class for {@link X509v2CRLBuilder}.
 */
public class X509v2CRLBuilderBC implements IX509v2CRLBuilder {
    private final X509v2CRLBuilder builder;

    /**
     * Creates new wrapper instance for {@link X509v2CRLBuilder}.
     *
     * @param builder {@link X509v2CRLBuilder} to be wrapped
     */
    public X509v2CRLBuilderBC(X509v2CRLBuilder builder) {
        this.builder = builder;
    }

    /**
     * Creates new wrapper instance for {@link X509v2CRLBuilder}.
     *
     * @param x500Name X500Name wrapper to create {@link X509v2CRLBuilder}
     * @param date     Date to create {@link X509v2CRLBuilder}
     */
    public X509v2CRLBuilderBC(IX500Name x500Name, Date date) {
        this(new X509v2CRLBuilder(((X500NameBC) x500Name).getX500Name(), date));
    }

    /**
     * Gets actual org.bouncycastle object being wrapped.
     *
     * @return wrapped {@link X509v2CRLBuilder}.
     */
    public X509v2CRLBuilder getBuilder() {
        return builder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IX509v2CRLBuilder addCRLEntry(BigInteger bigInteger, Date date, int i) {
        builder.addCRLEntry(bigInteger, date, i);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IX509v2CRLBuilder addExtension(IASN1ObjectIdentifier objectIdentifier, boolean isCritical,
                                          IASN1Encodable extension) throws IOException {
        builder.addExtension(((ASN1ObjectIdentifierBC) objectIdentifier).getASN1ObjectIdentifier(), isCritical,
                ((ASN1EncodableBC) extension).getEncodable());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IX509v2CRLBuilder setNextUpdate(Date nextUpdate) {
        builder.setNextUpdate(nextUpdate);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IX509CRLHolder build(IContentSigner signer) {
        return new X509CRLHolderBC(builder.build(((ContentSignerBC) signer).getContentSigner()));
    }

    /**
     * Indicates whether some other object is "equal to" this one. Compares wrapped objects.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        X509v2CRLBuilderBC that = (X509v2CRLBuilderBC) o;
        return Objects.equals(builder, that.builder);
    }

    /**
     * Returns a hash code value based on the wrapped object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(builder);
    }

    /**
     * Delegates {@code toString} method call to the wrapped object.
     */
    @Override
    public String toString() {
        return builder.toString();
    }
}
