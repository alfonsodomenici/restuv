/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restuv.uv;

/**
 *
 * @author alfonso
 */
public class MisuraCalcolata {
    
    public int quota;
    public double uv;

    public MisuraCalcolata(int quota, double rdiretta, double rdiffusa) {
        this.quota = quota;
        this.uv = rdiretta + rdiffusa;
    }  
}
