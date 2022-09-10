package polymorphism;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV{

    @Resource(name="sony")
    private Speaker speaker;

    public void powerOn(){
        System.out.println("엘지티비 --- 전원 켠다.");
    }

    public void powerOff(){
        System.out.println("엘지엘지 --- 전원 끈다.");
    }

    public void volumeUp(){
        speaker.volumeUp();
    }

    public void volumeDown(){
        speaker.volumeDown();
    }

}
