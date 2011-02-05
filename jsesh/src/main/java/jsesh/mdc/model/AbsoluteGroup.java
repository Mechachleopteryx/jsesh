/*
 * Created on 21 oct. 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package jsesh.mdc.model;

import jsesh.mdc.interfaces.AbsoluteGroupInterface;

/**
 * A group of signs with explicit placement. NOTE : should this be limited to
 * hieroglyphs ??? IMPORTANT : currently, we need at least two signs in an
 * absolute group. This is not a
 * 
 * @author rosmord
 *  
 */

public class AbsoluteGroup extends InnerGroup implements AbsoluteGroupInterface {

    public void addHieroglyph(Hieroglyph h) {
        addChild(h);
    }

    public void addHieroglyphAt(int idx, Hieroglyph h) {
        addChildAt(idx, h);
    }

    public void removeHieroglyphAt(int idx) {
        super.removeChildAt(idx);
    }

    public void removeHieroglyph(Hieroglyph h) {
        super.removeChild(h);
    }

    public Hieroglyph getHieroglyphAt(int idx) {
        return (Hieroglyph) getChildAt(idx);
    }

    public void accept(ModelElementVisitor v) {
        v.visitAbsoluteGroup(this);
    }

    public String toString() {
        return "(absolute " + getChildrenAsString() + ")";
    }

    /*
     * (non-Javadoc)
     * 
     * @see jsesh.mdc.model.ModelElement#compareToAux(jsesh.mdc.model.ModelElement)
     */
    public int compareToAux(ModelElement e) {
        return compareContents(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see jsesh.mdc.model.ModelElement#deepCopy()
     */
    public ModelElement deepCopy() {
        AbsoluteGroup r = new AbsoluteGroup();
        copyContentTo(r);
        return r;
    }

    public boolean containsOnlyOneSign() {
    	return getNumberOfChildren() == 1;
    }
    
    /**
     * Move the group elements in order to have a tight boundingbox around it.
     *  
     */
    public void compact() {
        if (getNumberOfChildren() > 0) {
            int x = getHieroglyphAt(0).getX();
            int y = getHieroglyphAt(0).getY();

            for (int i = 1; i < getNumberOfChildren(); i++) {
                if (getHieroglyphAt(i).getX() < x)
                    x = getHieroglyphAt(i).getX();
                if (getHieroglyphAt(i).getY() < y)
                    y = getHieroglyphAt(i).getY();
            }
            for (int i = 0; i < getNumberOfChildren(); i++) {
                Hieroglyph h= getHieroglyphAt(i);
                h.setExplicitPosition(h.getX() - x, h.getY()- y, h.getRelativeSize());           
            }
        }
    }
}