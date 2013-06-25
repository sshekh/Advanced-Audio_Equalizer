import processing.core.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import static processing.core.PConstants.CORNERS;

public class FourierFilter extends PApplet
{
float[] out1=new float[1024];
float[] out2=new float[1024];
Signal signal;
FFT fft1,fft2;
Minim minim;
AudioPlayer input;
AudioOutput output;
AudioOutput pseudooutput;
boolean click=false;
int startx=0,starty=0;
boolean start=false;
boolean release=false;
int releasex=-1,releasey=-1;
boolean filter=false;
boolean click2=false;
boolean click1=false;
String str="solo";
String setstring;
boolean stopped=false;
public FourierFilter(String s1)
{
    setstring=s1;
}
public void setup()
{
    rectMode(CORNERS);
    fft1=new FFT(1024,44100);
    fft2=new FFT(1024,44100);
    size(fft1.specSize(),600);
    minim=new Minim(this);
    //input=minim.loadFile("C:\\Users\\akashb\\Music\\Music\\Alvin Lee\\The Bluest Blues.mp3");
    input=minim.loadFile(setstring);
    input.play();
    input.mute();
    output=minim.getLineOut(Minim.STEREO, 1024);
    pseudooutput=minim.getLineOut(Minim.STEREO, 1024);
}


public void draw()
{
    background(0);
    stroke(255);
    fft1.forward(input.mix);
    fft2.forward(input.mix);
    for(int i=0;i<fft1.specSize();i++)
    {
        line(i,height,i,height-fft1.getBand(i)*4);
    }
    if(click==true)
    {
        input.mute();
        if(release==true && releasex!=-1 && releasey!=-1 && click2==false)
        {
            output.mute();
            pseudooutput.unmute();
            pseudooutput.clearSignals();
            if(startx!=releasex)
            {
            line(startx,0,startx,height);
            line(releasex,0,releasex,height);
            }
            if(startx>releasex)
            {
              for(int j=0;j<fft2.specSize();j++)
                    {
                        if(str=="solo"){
                               if(!(j>=releasex && j<=startx))
                               fft2.setBand(j,0);
                        }
                        else{
                            if((j>=releasex && j<=startx))
                            fft2.setBand(j,0);
                        }
                    }
               }
            if(startx<releasex)
            {
                for(int j=0;j<fft2.specSize();j++)
                 {
                     if(str == "solo"){
                         
                     if(!(j>=startx && j<=releasex))
                        fft2.setBand(j,0);
                     }
                     else{
                         if((j>=startx && j<=releasex))
                        fft2.setBand(j,0);
                     }
                }
                }
        
            fft2.inverse(out2);
            signal=new Signal(out2);
            pseudooutput.addSignal(signal);
    }
        else
        {
            pseudooutput.mute();
            output.unmute();
            output.clearSignals();
            fft1.inverse(out1);
            signal=new Signal(out1);
            output.addSignal(signal);
        }
    }
}

public void mouseDragged()
{
    if(start==false)
    {
        release=false;
        click2=false;
        releasex=-1;
        releasey=-1;
        startx=mouseX;
        starty=mouseY;
        start=true;
        click1=true;
    }
    rect(startx,starty,mouseX,mouseY);
}

public void mouseClicked()
{
    if(click==false)
    {
        click=true;
        click1=true;
    }
    else
    click2=true;
}

public void mouseReleased()
{
    if(click1==true)
    {
    release=true;
    releasex=mouseX;
    releasey=mouseY;
    start=false;
    }
}

public void changeString(String s)
{
    str=s;
}

public void changeBool(boolean value)
{
    if(value==true)
    {
        input.mute();
        output.close();
        pseudooutput.close();
    }
    else
    {
        input.unmute();
        click=false;
         output=minim.getLineOut(Minim.STEREO, 1024);
    pseudooutput=minim.getLineOut(Minim.STEREO, 1024);//extra code
    }
}
public void songPause()
    {
        input.pause();
        stopped=false;
    }
    public void songResume()
    {
        if(stopped==false)
        input.play();
        else
        {
            //song=minim.loadFile("C:\\Users\\akashb\\Music\\Music\\Alvin Lee\\The Bluest Blues.mp3",1024);
           input=minim.loadFile(setstring);
            input.play();
        }
    }
    public void songStop()
    {
        input.close();
        stopped=true;
    }
public void stop()
{
    input.close();
    output.close();
    minim.stop();
    super.stop();
}
}
