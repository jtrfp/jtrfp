/*
 * This file is part of jtrfp.
 *
 * jtrfp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jtrfp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jtrfp.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jtrfp.jtrfp.sit;

import org.jtrfp.jtrfp.Vertex3f;

public class SitBox {

	private Vertex3f position;

	private String modelName;

	private float theta;

	private float phi;

	private float psi;

	public float getPhi() {
		return phi;
	}

	public void setPhi(float phi) {
		this.phi = phi;
	}

	public float getPsi() {
		return psi;
	}

	public void setPsi(float psi) {
		this.psi = psi;
	}

	public Vertex3f getPosition() {
		return position;
	}

	public float getTheta() {
		return theta;
	}

	public void setTheta(float theta) {
		this.theta = theta;
	}

	public String getModelName() {
		return modelName;
	}

	public void setPosition(Vertex3f position) {
		this.position = position;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
