package org.pp.swing.model;

import java.io.Serializable;

/**
 * 报文,能进行网络传输
 */
public interface Message extends Serializable {

    /**
     * 换行符
     */
    String CR_LF = System.getProperty("line.separator");

    default void handle() {
        System.out.println("message can be handle.");
    }
}
