package com.springbook.zout;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

public class SerialTest implements SerialPortEventListener {
    SerialPort serialPort;
    private BufferedReader input;
    private OutputStream output;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    private static final String PORT_NAMES[] = { 
            "COM5"
    };

    public void initialize() {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            
            
            
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }

        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                System.out.println(inputLine);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    public void sendData(String data) {
        try {
            output.write(data.getBytes());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        SerialTest main = new SerialTest();
        main.initialize();
        Thread t = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();
        System.out.println("Started");

        // 아두이노로 1을 전송
        main.sendData("1\n");
    }
}
