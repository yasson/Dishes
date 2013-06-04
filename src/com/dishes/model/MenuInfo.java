/**
 * @author SenYang
 */
package com.dishes.model;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

/**
 * @author SenYang
 * 
 */
public class MenuInfo {

	private String description;
	private String menuId;
	private String menuName;
	private String pic;
	private List<AccessoriesInfo> listA;
	private List<IngredientsInfo> listI;
	private List<SeasoningInfo> listS;
	private List<StepsInfo> listStep;


	public MenuInfo( SoapObject so ) {

		listA = new ArrayList<MenuInfo.AccessoriesInfo>();
		listI = new ArrayList<MenuInfo.IngredientsInfo>();
		listS = new ArrayList<MenuInfo.SeasoningInfo>();
		listStep = new ArrayList<MenuInfo.StepsInfo>();
		this.setDescription( so.getPropertyAsString( "description" ) );
		this.setMenuId( so.getPropertyAsString( "menuId" ) );
		this.setMenuName( so.getPropertyAsString( "menuName" ) );
		this.setPic( so.getPropertyAsString( "pic" ) );
		for( int i = 0; i < so.getPropertyCount(); i++ ) {
			PropertyInfo propertyInfo = new PropertyInfo();
			so.getPropertyInfo( i, propertyInfo );
			if( propertyInfo.getName().contains( "ingredients" ) ) {
				listI.add( new IngredientsInfo( ( SoapObject )so.getProperty( i ) ) );

			}
			if( propertyInfo.getName().contains( "accessories" ) ) {
				listA.add( new AccessoriesInfo( ( SoapObject )so.getProperty( i ) ) );

			}
			if( propertyInfo.getName().contains( "seasoning" ) ) {
				listS.add( new SeasoningInfo( ( SoapObject )so.getProperty( i ) ) );

			}
			if( propertyInfo.getName().contains( "steps" ) ) {
				listStep.add( new StepsInfo( ( SoapObject )so.getProperty( i ) ) );

			}
		}

	}


	/**
	 * @return the listA
	 */
	public List<AccessoriesInfo> getListA() {

		return listA;
	}


	/**
	 * @return the listI
	 */
	public List<IngredientsInfo> getListI() {

		return listI;
	}


	/**
	 * @return the listS
	 */
	public List<SeasoningInfo> getListS() {

		return listS;
	}


	/**
	 * @return the listStep
	 */
	public List<StepsInfo> getListStep() {

		return listStep;
	}


	public String getDescription() {

		return description;
	}


	public void setDescription( String description ) {

		this.description = description;
	}


	public String getMenuId() {

		return menuId;
	}


	public void setMenuId( String menuId ) {

		this.menuId = menuId;
	}


	public String getMenuName() {

		return menuName;
	}


	public void setMenuName( String menuName ) {

		this.menuName = menuName;
	}


	public String getPic() {

		return pic;
	}


	public void setPic( String pic ) {

		this.pic = pic;
	}


	/**
	 * 辅料
	 * 
	 * @author SenYang
	 * 
	 */
	public class AccessoriesInfo {

		private String inid;
		private String ismajor;
		private String name;
		private String units;
		private String volume;
		private String finalcount;


		/**
		 * @param property
		 */
		public AccessoriesInfo( SoapObject property ) {

			// for( int i = 0; i < property.getPropertyCount(); i++ ) {
			// PropertyInfo propertyInfo=new PropertyInfo();
			// property.getPropertyInfo( i, propertyInfo );
			// if( propertyInfo.getName().contains( "inid" ) ) {
			// this.setInid( property.getPropertyAsString( "inid" ) );
			// }
			// if( propertyInfo.getName().contains( "ismajor" ) ) {
			// this.setInid( property.getPropertyAsString( "ismajor" ) );
			// }
			// if( propertyInfo.getName().contains( "name" ) ) {
			// this.setInid( property.getPropertyAsString( "name" ) );
			// }
			// if( propertyInfo.getName().contains( "units" ) ) {
			// this.setInid( property.getPropertyAsString( "units" ) );
			// }
			// if( propertyInfo.getName().contains( "volume" ) ) {
			// this.setInid( property.getPropertyAsString( "volume" ) );
			// }
			// if( propertyInfo.getName().contains( "finalcount" ) ) {
			// this.setInid( property.getPropertyAsString( "finalcount" ) );
			// }
			// }

			this.setInid( property.getPropertySafelyAsString( "inid" ) );
			this.setIsmajor( property.getPropertySafelyAsString( "ismajor" ) );
			this.setName( property.getPropertySafelyAsString( "name" ) );
			this.setUnits( property.getPropertySafelyAsString( "units" ) );
			this.setVolume( property.getPropertySafelyAsString( "volume" ) );
			this.setFinalcount( property.getPropertySafelyAsString( "finalcount" ) );

		}


		public String getInid() {

			return inid;
		}


		public void setInid( String inid ) {

			this.inid = inid;
		}


		public String getIsmajor() {

			return ismajor;
		}


		public void setIsmajor( String ismajor ) {

			this.ismajor = ismajor;
		}


		public String getName() {

			return name;
		}


		public void setName( String name ) {

			this.name = name;
		}


		public String getVolume() {

			return volume;
		}


		public void setVolume( String volume ) {

			this.volume = volume;
		}


		public String getFinalcount() {

			return finalcount;
		}


		public void setFinalcount( String finalcount ) {

			this.finalcount = finalcount;
		}


		public String getUnits() {

			return units;
		}


		public void setUnits( String units ) {

			this.units = units;
		}

	}

	/**
	 * 主料
	 * 
	 * @author SenYang
	 * 
	 */
	public class IngredientsInfo {

		private String inid;
		private String ismajor;
		private String name;
		private String units;
		private String volume;
		private String finalcount;


		/**
		 * @param property
		 */
		public IngredientsInfo( SoapObject property ) {

			this.setInid( property.getPropertySafelyAsString( "inid" ) );
			this.setIsmajor( property.getPropertySafelyAsString( "ismajor" ) );
			this.setName( property.getPropertySafelyAsString( "name" ) );
			this.setUnits( property.getPropertySafelyAsString( "units" ) );
			this.setVolume( property.getPropertySafelyAsString( "volume" ) );
			this.setFinalcount( property.getPropertySafelyAsString( "finalcount" ) );
		}


		public String getInid() {

			return inid;
		}


		public void setInid( String inid ) {

			this.inid = inid;
		}


		public String getIsmajor() {

			return ismajor;
		}


		public void setIsmajor( String ismajor ) {

			this.ismajor = ismajor;
		}


		public String getName() {

			return name;
		}


		public void setName( String name ) {

			this.name = name;
		}


		public String getVolume() {

			return volume;
		}


		public void setVolume( String volume ) {

			this.volume = volume;
		}


		public String getFinalcount() {

			return finalcount;
		}


		public void setFinalcount( String finalcount ) {

			this.finalcount = finalcount;
		}


		public String getUnits() {

			return units;
		}


		public void setUnits( String units ) {

			this.units = units;
		}

	}

	public class Nutritionbean {

	}

	/**
	 * 调料
	 * 
	 * @author SenYang
	 * 
	 */
	public class SeasoningInfo {

		private String inid;
		private String ismajor;
		private String name;
		private String units;
		private String volume;
		private String finalcount;


		/**
		 * @param property
		 */
		public SeasoningInfo( SoapObject property ) {

			this.setInid( property.getPropertySafelyAsString( "inid" ) );
			this.setIsmajor( property.getPropertySafelyAsString( "ismajor" ) );
			this.setName( property.getPropertySafelyAsString( "name" ) );
			this.setUnits( property.getPropertySafelyAsString( "units" ) );
			this.setVolume( property.getPropertySafelyAsString( "volume" ) );
			this.setFinalcount( property.getPropertySafelyAsString( "finalcount" ) );

		}


		public String getInid() {

			return inid;
		}


		public void setInid( String inid ) {

			this.inid = inid;
		}


		public String getIsmajor() {

			return ismajor;
		}


		public void setIsmajor( String ismajor ) {

			this.ismajor = ismajor;
		}


		public String getName() {

			return name;
		}


		public void setName( String name ) {

			this.name = name;
		}


		public String getVolume() {

			return volume;
		}


		public void setVolume( String volume ) {

			this.volume = volume;
		}


		public String getFinalcount() {

			return finalcount;
		}


		public void setFinalcount( String finalcount ) {

			this.finalcount = finalcount;
		}


		public String getUnits() {

			return units;
		}


		public void setUnits( String units ) {

			this.units = units;
		}

	}

	/**
	 * 制作步骤
	 * 
	 * @author SenYang
	 * 
	 */
	public class StepsInfo {

		private String menuId;
		private String note;
		private String sn;
		private String steppic;


		/**
		 * @param property
		 */
		public StepsInfo( SoapObject property ) {

			this.setMenuId( property.getPropertySafelyAsString( "menuId" ) );
			this.setNote( property.getPropertySafelyAsString( "note" ) );
			this.setSn( property.getPropertySafelyAsString( "sn" ) );
			this.setSteppic( property.getPropertySafelyAsString( "steppic" ) );

		}


		public String getMenuId() {

			return menuId;
		}


		public void setMenuId( String menuId ) {

			this.menuId = menuId;
		}


		public String getNote() {

			return note;
		}


		public void setNote( String note ) {

			this.note = note;
		}


		public String getSn() {

			return sn;
		}


		public void setSn( String sn ) {

			this.sn = sn;
		}


		public String getSteppic() {

			return steppic;
		}


		public void setSteppic( String steppic ) {

			this.steppic = steppic;
		}

	}

}
