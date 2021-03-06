package activeSegmentation.filterImpl;

import ij.ImageStack;
import weka.core.DenseInstance;

/**
 * 				
 *   
 * 
 * @author Sumit Kumar Vohra and Dimiter Prodanov , IMEC
 *
 *
 * @contents
 * Util for Filter Manager
 * 
 * 
 * @license This library is free software; you can redistribute it and/or
 *      modify it under the terms of the GNU Lesser General Public
 *      License as published by the Free Software Foundation; either
 *      version 2.1 of the License, or (at your option) any later version.
 *
 *      This library is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *       Lesser General Public License for more details.
 *
 *      You should have received a copy of the GNU Lesser General Public
 *      License along with this library; if not, write to the Free Software
 *      Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
public class FilterUtil {

	
	
	/**
	 * Create instance (feature vector) of a specific coordinate
	 * 
	 * @param x x- axis coordinate
	 * @param y y- axis coordinate
	 * @param classValue class value to be assigned
	 * @return corresponding instance
	 */
	public DenseInstance createInstance(
			int x, 
			int y, 
			int classValue,
			ImageStack stack,
			boolean colorFeatures,
			boolean oldColorFormat )
	{
		
		final int size=stack.getSize();

		final double[] values = new double[ size + 1 ];
		int n = 0;

		if( colorFeatures == false || oldColorFormat == true)
		{
			for (int z=0; z<size; z++, n++)		
				values[ z ] = stack.getVoxel( x, y, z );
		}
		else
		{
			for (int z=0; z <  size; z++, n++)		
			{
				int c  = (int) stack.getVoxel( x, y, z );
				int r = (c&0xff0000)>>16;
			int g = (c&0xff00)>>8;
		int b = c&0xff;
		values[ z ] = (r + g + b) / 3.0;
			}
		}


		// Assign class
		values[values.length-1] = (double) classValue;

		return new DenseInstance(1.0, values);
	}

	
	
	
	
	
	
	

}
