package com.hayat.pages;

import com.hayat.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	public BasePage() {
		PageFactory.initElements(Driver.get(),this);
	}
}
