package com.xinfu.demo3;

import java.util.ArrayList;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/05
 *     desc   :
 * </pre>
 */
public class PersonRelationResponse {
    public ArrayList<PersonRelationInfo> data;
    public PersonRelationInfo my;

    public void createBitmap() {
        my.convertUrlToBitmap();
        for (PersonRelationInfo info : data) {
            info.convertUrlToBitmap();
            for (PersonRelationInfo sonInfo : info.son) {
                sonInfo.convertUrlToBitmap();
            }
        }
    }
}
