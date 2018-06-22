package com.neusoft.duanmlssh.platform.core.db.interImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;



public abstract class BaseDao<T> extends BaseDaoInterImpl<T> {

	private HibernateTemplate hibernateTemplate;
	private JdbcTemplate jdbcTemplate;

	@Override
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setHibernateTemplate(
			@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Autowired
	public void setJdbcTemplate(
			@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}

