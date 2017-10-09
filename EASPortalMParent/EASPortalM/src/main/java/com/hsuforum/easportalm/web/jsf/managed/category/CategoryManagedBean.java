package com.hsuforum.easportalm.web.jsf.managed.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Category;
import com.hsuforum.easportalm.service.CategoryService;
import com.hsuforum.easportalm.web.vo.CategoryVo;
import com.hsuforum.easportalm.web.vowrapper.CategoryVoWrapper;

	



@ManagedBean
@SessionScoped
public class CategoryManagedBean extends TemplatePrimeDataTableManagedBean
	<Category, java.lang.String, CategoryService>{

	private static final long serialVersionUID = 1L;

	// 新增或修改狀態，新增為Create，修改為Update，用於detail時返回正確路徑用
	private String mode;



	//managedBean的主要使用service
	@ManagedProperty(value="#{categoryService}")
    private CategoryService service;



	/**
	 * 建構子
	 */
	public CategoryManagedBean() {
		
		super();

	}
	
	/**
	 * 初始設定
	 */
	@PostConstruct
	public void init() {
		//設定是否一進頁面，即秀出資料
		this.setInitShowListData(true);
		//初始話查詢條件
		this.initFindCriteriaMap();
		//設定VoWrapper
		this.setVoWrapper(new CategoryVoWrapper());
	
	}
	
	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

		

	/**
	 * 初始新增物件
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#initCreatingData()
	 *
	 */
	@Override
	protected void initCreatingData() {
		Category object = new Category();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));
		
		this.setMode("Create");
		
		}
	
	/**
	 * 如需要再create or update按鈕按下後，對updating date進行處理，則需要撰寫此部分。但不管怎樣都需要override此method
	 * 
	 */
	@Override
	protected void initUpdatingData(ValueObject<Category, java.lang.String> updatingData) {
	

		this.setMode("Update");
		
		}

    /**
	 * 在read頁面下，如有查詢輸入時，則續在建構子呼叫此函數來設定findCriteriaMap
	 * 如需要可斟酌修改
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {
		//目前只能先用String
		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();
		
		
								
					findCriteriaMap.put("name", null);
						
				
								
					findCriteriaMap.put("code", null);
						
				
								
					findCriteriaMap.put("sequence", null);
						
					
		this.setFindCriteriaMap(findCriteriaMap);
		//設定操作
        Map<String, String> findOperMap = new HashMap<String, String>();    

    		findOperMap.put("name", "eq");		
			findOperMap.put("code", "eq");		
			findOperMap.put("sequence", "eq");		
	        
        this.setFindOperMap(findOperMap);
        
        //設定排序
        Map<String, String> findSortMap = new HashMap<String, String>();
        
    		findSortMap.put("name", "DESC");		
			findSortMap.put("code", "DESC");		
			findSortMap.put("sequence", "DESC");		
	        
        this.setFindSortMap(findSortMap);
	}

	/**
	 * getUpdatingData 和 setUpdatingData 其實可以不需要, 因為 super class 已經有了 存在的目的,
	 * 只是為了讓 IDE 知道它的確切的type為何, 讓 jsf 的頁面比較好拖拉
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#getUpdatingData()
	 *
	 */
	@Override
	public CategoryVo getUpdatingData() {
		return (CategoryVo)super.getUpdatingData();
	}
	
	/**
	 * 設定UpdatingData
	 * 
	 */
	@Override
	public void setUpdatingData(ValueObject<Category, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

    /**
     * 取得Service物件
     */
    @Override
    public CategoryService getService() {

        return this.service;
    }

    /**
     * 設定該ManagedBean的主要service
     * @param service
     */
    @Override
    public void setService(CategoryService service) {
        this.service = service;
    }




	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {

	}
	
	/**
	 * 假如Entity(Business Object)，有Many-to-One或Many-to-Many的狀況，
	 * 則Code Generator會自動Override以供修改使用，可自行改成需要的Method，
	 * 主要作用是在read頁面就Fetch所有相關關聯的資料，免得再update頁面出現問題
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<Category> findAllData() {
		return this.getService().findAllFetchRelation();
	}
	
	
}


