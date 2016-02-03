/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inv.init;


/**
 *
 * @author tharaka
 */
public class Status {

        public static final String ACTIVE                   = "01";
        public static final String INACTIVE                 = "02";
        public static final String REQUEST_BY_RA_USER       = "03";
        public static final String APPROVE_BY_RA            = "04";
        public static final String REJECT_BY_RA             = "05";
        public static final String APPROVE_BY_CA            = "06";
        public static final String REJECT_BY_CA             = "07";
        public static final String REVOKE_BY_CA             = "08";
        public static final String APPROVE_BY_DRM           = "09";
        public static final String REJECT_BY_DRM            = "10";
        public static final String APPROVE_BY_TPU           = "11";
        public static final String REJECT_BY_TPU            = "12";
        public static final String EXPIRED                  = "13";
        public static final String DELETED                  = "14";
        public static final String RESTORE                  = "15";
        public static final String BACKUP                   = "16";

        //////////////////////
        //ocsp Responce status
        //////////////////////
        public static final String OCSP_GOOD                = "01";
        public static final String OCSP_BAD                 = "02";
        public static final String OCSP_UNKNOWN             = "03";
        
}

