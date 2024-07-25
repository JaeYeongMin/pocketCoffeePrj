package com.springbook.zout;


import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;


import java.io.OutputStream;

public class ArduinoMotorControl {

	// private static final String PORT_NAME = "USB-SERIAL CH340(COM5)"; // 포트 이름은 각자의 환경에 맞게 변경
	private static final String PORT_NAME = "COM5"; // 포트 이름은 각자의 환경에 맞게 변경
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;

    private SerialPort serialPort;
    private OutputStream outputStream;

    
    public static void main(String[] args) throws InterruptedException {
        ArduinoMotorControl motorControl = new ArduinoMotorControl();
        
        Thread.sleep(2100);
        
        // 모터를 전진(1)으로 동작시키기
        motorControl.sendCommand('1');
        Thread.sleep(5000); // 5초간 모터 동작
        // 모터를 정지(0)시키기
        motorControl.sendCommand('0');
        
        motorControl.serialPort.close(); // 시리얼 포트 닫기
    }    
    
    public ArduinoMotorControl() {
        initialize();
    }

    private void initialize() {
        try {
            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(PORT_NAME);
            String className =this.getClass().getName();
            System.out.println(portId.getName());
            //className = "sketch_jul25b";
            
            serialPort = (SerialPort) portId.open(className, TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            outputStream = serialPort.getOutputStream();
            
            System.out.println("시리얼통신 성공!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    

    
    
    public void sendCommand(char command) {
        try {
        	System.out.println("===========전송============");
        	System.out.println(command);
        	
            outputStream.write(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
