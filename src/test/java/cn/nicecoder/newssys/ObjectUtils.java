package cn.nicecoder.newssys;

import lombok.extern.slf4j.Slf4j;
 
import java.lang.reflect.Field;
 
@Slf4j
public class ObjectUtils {
 
	private ObjectUtils() {
	}
 
	/**
	 * 判断类中每个属性是否都为空
	 *
	 * @param
	 * @return
	 */
	public static boolean checkAllFieldIsNull(Object obj) throws IllegalAccessException{
		boolean flag = true;
		for(Field f : obj.getClass().getDeclaredFields()){
			f.setAccessible(true);
			if(f.get(obj) != null){
				flag = false;
				return flag;
			}
		}

		return flag;
	}


}