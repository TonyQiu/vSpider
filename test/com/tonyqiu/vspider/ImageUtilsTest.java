package com.tonyqiu.vspider;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImageUtilsTest {

	@Test
	public void testReverImage() {
		System.out
				.println(ImageUtils
						.reverImage(
								"http://img02.taobaocdn.com/imgextra/i2/16519030670464046/T1U6.VXkdbXXXXXXXX_!!10986519-0-pix.jpg",
								"1", "1", "1")

				);
	}

}
