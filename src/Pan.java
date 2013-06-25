import processing.core.*;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class Pan extends PApplet
{
    Minim minim;
    AudioPlayer song; 
    int value;
    boolean stopped=false;
    String str;String str2;
    public Pan(String s1,String s2)
    {
        str=s2;
        str2=s1;
    }
    public void setup()
    {
        size(548,214);
        minim=new Minim(this);
        //song=minim.loadFile("C:\\Users\\akashb\\Music\\Music\\Alvin Lee\\The Bluest Blues.mp3",1024);
        song=minim.loadFile(str2,1024);
        song.play();
     }
    public void draw()
    {
        background(0);
        stroke(255);
        for(int i=0;i<song.bufferSize()-1;i++)
            {
            float x1=map(i,0,song.bufferSize(),0,width);
            float x2=map(i+1,0,song.bufferSize(),0,width);
            line(x1,height/4-song.left.toArray()[i]*50,x2,height/4-song.left.toArray()[i+1]*50);//instead of toArray, get(int) can also be used
            line(x1,3*height/4-song.right.toArray()[i]*50,x2,3*height/4-song.right.toArray()[i+1]*50);
            }
    }
    public void changePan(float x)
    {
        song.setPan(x);
        song.hasControl(AudioPlayer.PAN);
    }
    public void changeBalance(float x)
    {
        song.setBalance(x);
    }
    public void songPause()
    {
        song.pause();
        stopped=false;
    }
    public void songResume()
    {
        if(stopped==false)
        song.play();
        else
        {
            //song=minim.loadFile("C:\\Users\\akashb\\Music\\Music\\Alvin Lee\\The Bluest Blues.mp3",1024);
            song=minim.loadFile(str2,1024);
            song.play();
        }
    }
    public void songStop()
    {
        song.close();
        stopped=true;
    }
    public String getPlay()
    {
        //str="The Bluest Blues";
        return str;
    }
    
    public void changeBool(boolean value)
{
    if(value==false)
        song.unmute();
    else
        song.mute();
}
    public void stop()
    {
        super.stop();
    }
}