/*
 * This file is part of mtmX.
 *
 * mtmX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mtmX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mtmX.  If not, see <http://www.gnu.org/licenses/>.
 */
package jtrfp.common.internal.trk;

import jtrfp.common.Vertex3f;
import jtrfp.common.trk.ITrkData;

public class TrkData implements ITrkData {

	private String tireModelBaseName;

	private String axleModelName;

	private String barTextureName;

	private String shockTextureName;

	private String truckModelBaseName;

	private String truckName;

	private Vertex3f axlebarOffset;

	private Vertex3f driveshaftPos;

	private Vertex3f leftFrontTirePos;

	private Vertex3f leftRearTirePos;

	private Vertex3f rightFrontTirePos;

	private Vertex3f rightRearTirePos;

	@Override
	public String getTireModelBaseName() {
		return tireModelBaseName;
	}

	@Override
	public String getAxleModelName() {
		return axleModelName;
	}

	@Override
	public String getBarTextureName() {
		return barTextureName;
	}

	@Override
	public String getShockTextureName() {
		return shockTextureName;
	}

	@Override
	public String getTruckModelBaseName() {
		return truckModelBaseName;
	}

	@Override
	public String getTruckName() {
		return truckName;
	}

	protected void setTireModelBaseName(String tireModelBaseName) {
		this.tireModelBaseName = tireModelBaseName;
	}

	protected void setAxleModelName(String axleModelName) {
		this.axleModelName = axleModelName;
	}

	protected void setBarTextureName(String barTextureName) {
		this.barTextureName = barTextureName;
	}

	protected void setShockTextureName(String shockTextureName) {
		this.shockTextureName = shockTextureName;
	}

	protected void setTruckModelBaseName(String truckModelBaseName) {
		this.truckModelBaseName = truckModelBaseName;
	}

	protected void setTruckName(String truckName) {
		this.truckName = truckName;
	}

	@Override
	public Vertex3f getAxlebarOffset() {
		return axlebarOffset;
	}

	@Override
	public Vertex3f getDriveshaftPos() {
		return driveshaftPos;
	}

	@Override
	public Vertex3f getLeftFrontTirePos() {
		return leftFrontTirePos;
	}

	@Override
	public Vertex3f getLeftRearTirePos() {
		return leftRearTirePos;
	}

	@Override
	public Vertex3f getRightFrontTirePos() {
		return rightFrontTirePos;
	}

	@Override
	public Vertex3f getRightRearTirePos() {
		return rightRearTirePos;
	}

	protected void setAxlebarOffset(Vertex3f axlebarOffset) {
		this.axlebarOffset = axlebarOffset;
	}

	protected void setDriveshaftPos(Vertex3f driveshaftPos) {
		this.driveshaftPos = driveshaftPos;
	}

	protected void setLeftFrontTirePos(Vertex3f leftFrontTirePos) {
		this.leftFrontTirePos = leftFrontTirePos;
	}

	protected void setLeftRearTirePos(Vertex3f leftRearTirePos) {
		this.leftRearTirePos = leftRearTirePos;
	}

	protected void setRightFrontTirePos(Vertex3f rightFrontTirePos) {
		this.rightFrontTirePos = rightFrontTirePos;
	}

	protected void setRightRearTirePos(Vertex3f rightRearTirePos) {
		this.rightRearTirePos = rightRearTirePos;
	}
}
