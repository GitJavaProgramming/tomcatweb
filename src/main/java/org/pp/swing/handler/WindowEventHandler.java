package org.pp.swing.handler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created with IntelliJ IDEA.
 * User: 45554
 * Date: 19-3-25
 * Time: 下午8:33
 * To change this template use File | Settings | File Templates.
 */
public class WindowEventHandler extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
