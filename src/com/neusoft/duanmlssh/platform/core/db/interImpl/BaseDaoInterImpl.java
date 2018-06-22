package com.neusoft.duanmlssh.platform.core.db.interImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.neusoft.duanmlssh.platform.core.db.MultipurposeDB;
import com.neusoft.duanmlssh.platform.core.db.inter.BaseDaoInter;
import com.neusoft.duanmlssh.platform.core.util.ActionUtil;

public abstract class BaseDaoInterImpl<T> implements BaseDaoInter<T> {
	
	public  abstract HibernateTemplate getHibernateTemplate();
	
	public abstract JdbcTemplate getJdbcTemplate();
	
	protected abstract Class<T> getEntityClass();
	
	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().delete(load(id));
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public T load(Serializable id) {
		return getHibernateTemplate().load(getEntityClass(), id);
	}

	@Override
	public T get(Serializable id) {
		return getHibernateTemplate().get(getEntityClass(), id);
	}

	public Criteria createCriteria() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(getEntityClass());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T findByField(String fieldName, Object fieldValue) {
		Criteria criteria = this.createCriteria().add(Restrictions.eq(fieldName, fieldValue));
		final Iterator<T> it = criteria.list().iterator();
		if (it.hasNext()) {
			return (T) it.next();
		}
		return null;
	}
	
	/**
	 * 
	* <p>@Description: 返回具有分页的查询结果集， </p>
	* <p> 并可传参 bind(true或false)来确定是否需要绑定映射Pojo</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	@SuppressWarnings("unchecked")
	protected PaginationInterImpl findBySQL(String sql,Integer pageNo,Integer pageSize,boolean bind){
		String totalCountSql = "select count(1) from ( " + sql + " )";
		SQLQuery  totalCountQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(totalCountSql);
		SQLQuery dataQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (bind) {
			dataQuery.addEntity(getEntityClass());
		}
		if (pageNo != null && pageSize != null) {
			dataQuery.setFirstResult((pageNo - 1) * pageSize);
			dataQuery.setMaxResults(pageSize);
		}
		int totalCount = ((Number)totalCountQuery.list().iterator().next()).intValue();
		List<T> data = dataQuery.list(); 
		return new PaginationInterImpl(pageNo, pageSize, totalCount, data);
	}
	
	@SuppressWarnings("unchecked")
	protected PaginationInterImpl findByParamsSQL(String sql,Object[] params,Integer pageNo,Integer pageSize,boolean bind){
		String totalCountSql = "select count(1) from ( " + sql + " )";
		SQLQuery totalCountQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(totalCountSql);
		SQLQuery dataQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (bind) {
			dataQuery.addEntity(getEntityClass());
		}
		if (pageNo != null && pageSize != null) {
			dataQuery.setFirstResult((pageNo - 1) * pageSize);
			dataQuery.setMaxResults(pageSize);
		}
		for (int i = 0; i < params.length; i++) {
			totalCountQuery.setParameter(i, params[i]);
			dataQuery.setParameter(i, params[i]);
		}
		dataQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		int totalCount = ((Number)totalCountQuery.list().iterator().next()).intValue();
		List<T> data = dataQuery.list();
		return new PaginationInterImpl(pageNo, pageSize, totalCount, data);
	}
	
	@SuppressWarnings("deprecation")
	protected PaginationInterImpl jdbcFind(String sql, Object[] args, Class<T> beanClass, final Integer pageNo, final Integer pageSize) {
		final String countSql = "select count(1) from ( " + sql + " )";
		final int totalCount = getJdbcTemplate().queryForInt(countSql, args);
		final List<T> data = new ArrayList<T>();
		RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(beanClass){
			@Override
			public T mapRow(ResultSet rs, int rowNumber) throws SQLException {
				if (rowNumber >= (pageNo - 1) * pageSize && rowNumber < pageNo * pageSize)
					data.add(super.mapRow(rs, rowNumber));
				return null;
			}
		};
		getJdbcTemplate().query(sql, args,rowMapper);
		return new PaginationInterImpl(pageNo, pageSize, totalCount, data);
	}
	
	protected int count(String sql){
		sql = "select count(1) from ( " + sql + " )";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return ((Number) query.list().iterator().next()).intValue();
	}
	
	protected Query createSQLQuery(String sql, boolean bind) {
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (bind){
			query.addEntity(getEntityClass());
		}
		return query;
	}
	
	/**
	 * 
	* <p>@Description:获取ClOB对象</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public Clob createClob(final String text){
		return getHibernateTemplate().execute(new HibernateCallback<Clob>() {
			public Clob doInHibernate(Session session) throws HibernateException,SQLException{
				return session.getLobHelper().createClob(text);
			}
		});
	}
	
	/**
	 * 
	* <p>@Description:获取BlOB对象</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public Blob createBlob(final MultipartFile file){
		return getHibernateTemplate().execute(new HibernateCallback<Blob>() {
			@Override
			public Blob doInHibernate(Session session) throws HibernateException,SQLException {
				try {
					return session.getLobHelper().createBlob(file.getInputStream(),file.getSize());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	
	/**
	 * 
	* <p>@Description:获取Blob对象</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	public Blob createBlob(final InputStream stream, final int size) {
		return getHibernateTemplate().execute(new HibernateCallback<Blob>() {
			public Blob doInHibernate(Session session) throws HibernateException, SQLException {
				return session.getLobHelper().createBlob(stream, size);
			}
		});
	}
	
	/**
	 * 
	* <p>@Description:根据MultipurposeDB对象来查找数据</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	@SuppressWarnings("unchecked")
	protected PaginationInterImpl findByMultipurposeDB(MultipurposeDB mudb,int pageNo,int pageSize){
		CriteriaImpl impl = (CriteriaImpl) mudb.criteria;
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List<CriteriaImpl.OrderEntry>) ActionUtil.getFieldValue(impl, "orderEntries");
			ActionUtil.setFieldValue(impl, "orderEntries", new ArrayList<CriteriaImpl.OrderEntry>());
		} catch (Exception e) {
			throw new RuntimeException("cannot read/write 'orderEntries' from CriteriaImpl", e);
		}
		int totalCount = ((Number) impl.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		PaginationInterImpl p = new PaginationInterImpl(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList<Object>());
			return p;
		}
		impl.setProjection(projection);
		if (projection == null) {
			impl.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			impl.setResultTransformer(transformer);
		}
		try {
			ActionUtil.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new RuntimeException("set 'orderEntries' to CriteriaImpl faild", e);
		}
		impl.setFirstResult(p.getFirstResult());
		impl.setMaxResults(p.getPageSize());
		p.setList(impl.list());
		return p;
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findByMultipurposeDB(MultipurposeDB mudb) {
		return mudb.criteria.list();
	}
	
	/**
	 * 
	* <p>@Description:传入MultipurposeDB对象，获取总共数据条数</p>
	* 
	* @author 段美林
	* @date  2014-8-31
	* @version 1.0
	 */
	protected Number countBricks(MultipurposeDB mudb) {
		mudb.rowCount();
		return (Number) mudb.criteria.list().iterator().next();
	}
	
}
