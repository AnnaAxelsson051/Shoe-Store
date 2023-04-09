package com.shopme.setting;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.GeneralSettingBag;
import com.shopme.common.entity.Setting;
import com.shopme.common.entity.SettingCategory;

@Service
public class SettingService {
	@Autowired private SettingRepository repo;
	
	
	//Returnmethod for the General settin object 
	//Adding settings and curency settings to model
	public List<Setting> getGeneralSettings() {
		
		
		return repo.findByTwoCategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);
	}
}