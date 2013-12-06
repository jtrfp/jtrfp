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
package org.jtrfp.jtrfp;

public class Vertex3f {

	private final float x;

	private final float y;

	private final float z;

	public Vertex3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public Vertex3f multiply(float factor) {
		Vertex3f vertex = new Vertex3f(
				x * factor,
				y * factor,
				z * factor
		);
		return vertex;
	}

	public static Vertex3f average(Vertex3f a, Vertex3f b) {
		Vertex3f vertex = new Vertex3f(
				(a.getX() + b.getX()) / 2,
				(a.getY() + b.getY()) / 2,
				(a.getZ() + b.getZ()) / 2
		);
		return vertex;
	}

	public static float distance(Vertex3f a, Vertex3f b) {
		return (float) Math.sqrt(
				Math.pow(b.getX() - a.getX(), 2) +
				Math.pow(b.getY() - a.getY(), 2) +
				Math.pow(b.getZ() - a.getZ(), 2));
	}

}
