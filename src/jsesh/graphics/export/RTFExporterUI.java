/**
 * author : Serge ROSMORDUC
 * This file is distributed according to the LGPL (GNU lesser public license)
 */
package jsesh.graphics.export;

import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.DefaultComboBoxModel;

import jsesh.graphics.export.RTFExportPreferences.RTFExportGranularity;
import jsesh.mdc.model.TopItemList;
import jsesh.mdcDisplayer.layout.SimpleViewBuilder;
import jsesh.mdcDisplayer.preferences.DrawingSpecification;
import jsesh.mdcDisplayer.swing.units.LengthUnit;
import jsesh.swingUtils.FileButtonMapper;

/**
 * Interface for RTF export to files.
 * TODO: create an export form using Netbeans.
 */
public class RTFExporterUI {
	File file;
	RTFExportPreferences rtfPreferences;

	/**
	 * @param file
	 * @param preferences
	 */
	public RTFExporterUI(File file, RTFExportPreferences preferences) {
		this.file = file;
		rtfPreferences = preferences;
	}

	/**
	 * @param drawingSpecifications
	 * @param model
	 */
	public void exportModel(DrawingSpecification drawingSpecifications,
			TopItemList model) {
		
		RTFSimpleExporter exporter= new RTFSimpleExporter();
		exporter.setDrawingSpecifications(drawingSpecifications);
		exporter.setRtfPreferences(rtfPreferences);
		exporter.setViewBuilder(new SimpleViewBuilder());
		try {
			OutputStream out = new FileOutputStream(file);
			exporter.ExportModelTo(model, out);
		} catch (IOException exception) {
			exception.printStackTrace();
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @param currentOutputDirectory
	 * @param string
	 */
	public void setFile(File currentOutputDirectory, String fname) {
		this.file = new File(currentOutputDirectory, fname);
	}


	/**
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param frame
	 * @param string
	 * @return
	 */
	public ExportOptionPanel getOptionPanel(Component parent, String title) {
		return new RTFExportOptionPanel(parent,title);
	}
	
	private class RTFExportOptionPanel extends ExportOptionPanel {
        JRTFFileExportPreferences form;
		LengthUnit unit= LengthUnit.POINT;
		/**
		 * @param parent
		 * @param title
		 */
		public RTFExportOptionPanel(Component parent, String title) {
			super(parent, title);
			form= new JRTFFileExportPreferences();

            form.getFileField().setValue(file);
            //			form.fileNameField.setValue(file);
			new FileButtonMapper(form.getBrowseButton(),form.getFileField());
			
			LengthUnit.attachToCombobox(form.getUnitCB(), unit);
			
			form.getCadratHeightField().setValue(unit.convert(rtfPreferences.getCadratHeight()));
			
			jsesh.mdcDisplayer.swing.units.UnitMaintainter.linkUnitsToValueField(form.getUnitCB(), form.getCadratHeightField());
			
			form.getExportModeCB().setModel(new DefaultComboBoxModel(RTFExportGranularity.GRANULARITIES));
			form.getExportModeCB().setSelectedItem(RTFExportGranularity.GROUPED_CADRATS);
			add(form);
		}

		/* (non-Javadoc)
		 * @see jsesh.graphics.export.ExportOptionPanel#setOptions()
		 */
		public void setOptions() {
			file= (File) form.getFileField().getValue();
			rtfPreferences.setCadratHeight(getCadratHeight());
			rtfPreferences.setExportGranularity((RTFExportGranularity) form.getExportModeCB().getSelectedItem());
			rtfPreferences.setRespectOriginalTextLayout(false); // Normally false. If we want better handling, we will use something else than RTF.
		}

		/**
		 * @return
		 */
		private int getCadratHeight() {
			return (int)(((Number)form.getCadratHeightField().getValue()).doubleValue() * ((LengthUnit)form.getUnitCB().getSelectedItem()).getPointsValue());
		}
	}

	
	public RTFExportPreferences getRtfPreferences() {
		return rtfPreferences;
	}

	public void setRtfPreferences(RTFExportPreferences rtfPreferences) {
		this.rtfPreferences = rtfPreferences;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
}
