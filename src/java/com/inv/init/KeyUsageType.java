/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inv.init;

/**
 *
 * @author tharaka
 */
public class KeyUsageType {
    
    public static final int EncipherOnly     = 1;
    public static final int DigitalSignature = 128;
    public static final int DataEncipherment = 16;
    public static final int CRLSign          = 2;
    public static final int KeyEncipherment  = 32;
    public static final int DecipherOnly     = 32768;
    public static final int KeyCertSign      = 4;
    public static final int NonRepudiation   = 64;
    public static final int KeyAgreement     = 8;
    
    public static final int CA_CERTSIGNING   = 1;
    public static final int NOT_CA_CERTSIGNING   = 0;
    
    
}
