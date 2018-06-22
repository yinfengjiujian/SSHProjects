package com.neusoft.duanmlssh.platform.core.db;


import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.neusoft.duanmlssh.platform.core.db.interImpl.BaseDaoInterImpl;

public class MultipurposeDB {
	
	public Criteria criteria;
	
	public MultipurposeDB(BaseDaoInterImpl<?> dao) {
		this.criteria = dao.createCriteria();
	}
	
	public MultipurposeDB asc(String field) {
		criteria.addOrder(Order.asc(field));
		return this;
	}
	
	public MultipurposeDB desc(String field){
		criteria.addOrder(Order.desc(field));
		return this;
	}
	
	public MultipurposeDB setMaxResults(int max) {
		criteria.setMaxResults(max);
		return this;
	} 
	
	/**
	 * 
	* <p>@Description:相当于 SQL中的 《 小于</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB in(String field, Object[] value) {
		if (conditionValueValid(value))
			if(value.length != 0){
				criteria.add(Restrictions.in(field, value));
			}else{
				criteria.add(Restrictions.sqlRestriction("1 = 0"));
			}
		return this; 
	}
	
	/**
	 * 
	* <p>@Description:相当于 SQL中的 = 等于</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB eq(String field, Object value) {
		if (conditionValueValid(value)){
			criteria.add(Restrictions.eq(field, value));
		}
		return this;
	}
	
	/**
	 * 
	* <p>@Description:相当于 SQL中的 》 大于</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB gt(String field, Object value) {
		if (conditionValueValid(value)){
			criteria.add(Restrictions.gt(field, value));
		}
		return this;
	}

	/**
	 * 
	* <p>@Description:相当于 SQL中的 》= 大于等于</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB ge(String field, Object value) {
		if (conditionValueValid(value)){
			criteria.add(Restrictions.ge(field, value));
		}
		return this;
	}

	/**
	 * 
	* <p>@Description:相当于 SQL中的 《= 小于等于</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB le(String field, Object value) {
		if (conditionValueValid(value)){
			criteria.add(Restrictions.le(field, value));
		}
		return this;
	}

	/**
	 * 
	* <p>@Description:相当于 SQL中的 《 小于</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB lt(String field, Object value) {
		if (conditionValueValid(value)){
			criteria.add(Restrictions.lt(field, value));
		}
		return this;
	}

	/**
	 * 
	* <p>@Description:相当于 SQL中的 like</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB like(String field, String value, MatchMode mode) {
		if (conditionValueValid(value)){
			criteria.add(Restrictions.like(field, value, mode));
		}
		return this;
	}
	
	/**
	 * 
	* <p>@Description:相当于 SQL中的 OR</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB recorlike(String field, String fieldor,String value, MatchMode mode) {
		if (conditionValueValid(value)){
			criteria.add(Restrictions.or(Restrictions.like(field, value, mode), Restrictions.like(fieldor, value, mode)));
		}
		return this;
	}

	/**
	 * 
	* <p>@Description:相当于 SQL中的  != </p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB ne(String field, Object value) {
		if (conditionValueValid(value)){
			criteria.add(Restrictions.not(Restrictions.eq(field, value)));
		}
		return this;
	}

	/**
	 * 
	* <p>@Description:相当于 SQL中的比如：  AAC002 IS NULL</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB isNull(String field) {
		criteria.add(Restrictions.isNull(field));
		return this;
	}

	/**
	 * 
	* <p>@Description:相当于 SQL中的比如：  AAC002 IS Not NULL</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB isNotNull(String field) {
		criteria.add(Restrictions.isNotNull(field));
		return this;
	}

	/**
	 * 
	* <p>@Description:</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public MultipurposeDB add(Criterion criterion) {
		criteria.add(criterion);
		return this;
	}

	public MultipurposeDB createAlias(String a, String b) {
		criteria = criteria.createAlias(a, b);
		return this;
	}

	public MultipurposeDB addProperty(String property) {
		criteria.setProjection(Projections.property(property));
		return this;
	}
	
	/**
	 * 
	* <p>@Description:某个字段分组</p>
	* 
	* @author 段美林
	* @date  2016-11-23
	* @version 1.0
	 */
	public MultipurposeDB addPropertyGruop(String field) {
		criteria.setProjection(Projections.groupProperty(field));
		return this;
	}

	public MultipurposeDB rowCount() {
		criteria.setProjection(Projections.rowCount());
		return this;
	}

	public MultipurposeDB setFirstResult(Integer startRow) {
		criteria.setFirstResult(startRow);
		return this;
	}

	public MultipurposeDB setFetchMode(String string, FetchMode join) {
		criteria.setFetchMode(string, join);
		return this;
	}

	private boolean conditionValueValid(Object value) {
		if (value instanceof String && StringUtils.isNotBlank((String) value))
			return true;
		if (value instanceof Number && value != null)
			return true;
		if (value instanceof Date && value != null)
			return true;
		if (value instanceof Timestamp && value != null)
			return true;
		if (value instanceof Boolean && value != null)
			return true;
		return value != null && !"".equals(value);
	}
}
