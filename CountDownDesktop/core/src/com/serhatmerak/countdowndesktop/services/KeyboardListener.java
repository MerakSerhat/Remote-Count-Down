package com.serhatmerak.countdowndesktop.services;

import com.serhatmerak.countdowndesktop.MainScreen;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.dispatcher.SwingDispatchService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardListener implements NativeKeyListener {
    private static final Logger log = Logger.getLogger(GlobalScreen.class.getPackage().getName());

    public MainScreen mainScreen;
    public boolean KeyboardListenerOn = true;
    private DoublePressDetector doublePressDetector;


    public KeyboardListener(MainScreen mainScreen) {
        this.mainScreen = mainScreen;

        doublePressDetector = new DoublePressDetector(this);
        // Disable parent logger and set the desired level.
        log.setUseParentHandlers(false);
        log.setLevel(Level.OFF);

        // Setup a generic ConsoleHandler
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        log.addHandler(handler);

        /* Note: JNativeHook does *NOT* operate on the event dispatching thread.
         * Because Swing components must be accessed on the event dispatching
         * thread, you *MUST* wrap access to Swing components using the
         * SwingUtilities.invokeLater() or EventQueue.invokeLater() methods.
         */
        GlobalScreen.setEventDispatcher(new SwingDispatchService());

        stateChanged();
    }

    public void stateChanged() {

        try {
            // Keyboard checkbox was changed, adjust listeners accordingly.
            if (KeyboardListenerOn) {
                GlobalScreen.registerNativeHook();
            } else {
                GlobalScreen.unregisterNativeHook();
            }
        } catch (NativeHookException ignored) {}

        GlobalScreen.addNativeKeyListener(this);
    }


    /**
     * @see NativeKeyListener#nativeKeyPressed(NativeKeyEvent)
     */
    public void nativeKeyPressed(NativeKeyEvent e) {
//        System.out.println(e.paramString());
        doublePressDetector.press(e.getKeyCode());
    }

    /**
     * @see NativeKeyListener#nativeKeyReleased(NativeKeyEvent)
     */
    public void nativeKeyReleased(NativeKeyEvent e) {
        doublePressDetector.release(e.getKeyCode());

    }

    /**
     * @see NativeKeyListener#nativeKeyTyped(NativeKeyEvent)
     */
    public void nativeKeyTyped(NativeKeyEvent e) {
//        mainScreen.mainScreenHuds.logLabel.setText("Typed: " + e.getKeyCode());

        System.out.println(e.paramString());
    }

}