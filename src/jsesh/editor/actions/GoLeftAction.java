/*
 * Created on 30 sept. 2004 by rosmord
 * This code can be distributed under the Gnu Library Public Licence.
 **/
package jsesh.editor.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jsesh.editor.JMDCEditor;

/**
 * TODO describe type
 * 
 * @author rosmord
 * 
 */
public class GoLeftAction extends EditorAction {

	public GoLeftAction(JMDCEditor editor) {
		super(editor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (editor.getDrawingSpecifications().getTextDirection().isLeftToRight()) {
			if (editor.getDrawingSpecifications().getTextOrientation()
					.isHorizontal())
				editor.getWorkflow().cursorPrevious();
			else
				editor.getWorkflow().cursorUp();
		} else {
			if (editor.getDrawingSpecifications().getTextOrientation()
					.isHorizontal()) {
				editor.getWorkflow().cursorNext();
			} else {
				editor.getWorkflow().cursorDown();
			}
		}
	}

}
