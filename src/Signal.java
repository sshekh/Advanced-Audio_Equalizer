import ddf.minim.AudioSignal;
import processing.core.*;
import static processing.core.PApplet.arraycopy;

class Signal extends PApplet implements AudioSignal
{
    float[] globalsample=new float[1024];
    
    public Signal(float[] localsample1)
    {
        arraycopy(localsample1,0,globalsample,0,globalsample.length); //edited from original
    }
    
    @Override
    public void generate(float[] localsample2) {
        arraycopy(globalsample,0,localsample2,0,localsample2.length);
    }

    @Override
    public void generate(float[] left, float[] right) {
        generate(left);
        generate(right);
    }
 }