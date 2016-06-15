package org.klarblick.document.watermark.service;

import org.klarblick.document.model.Document;
import org.klarblick.document.model.Watermark;

/**
 * Service to generate a {@link Watermark} for a {@link Document}.
 * This implementation is probably not finished yet. 
 * It is likely, that a Watermark of a document contains more than its attribute values,
 * e.g. a signature or checksum.
 * 
 * @author DTramnitzke
 *
 */
public class WatermarkService {

	public Watermark generateWatermark(Document document){
		
		document.generateWatermark();
		
		return document.getWatermark();
	}
}
