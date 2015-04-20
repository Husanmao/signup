package com.colin.domain;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Record entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.colin.domain.Record
 * @author MyEclipse Persistence Tools
 */

public class RecordDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(RecordDAO.class);
	// property constants
	public static final String WEIXIN_ID = "weixinId";
	public static final String REAL_PLACE = "realPlace";
	public static final String WORK_PLACE = "workPlace";
	public static final String EFFECTIVENESS = "effectiveness";

	protected void initDao() {
		// do nothing
	}

	public void save(Record transientInstance) {
		log.debug("saving Record instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Record persistentInstance) {
		log.debug("deleting Record instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Record findById(java.lang.Integer id) {
		log.debug("getting Record instance with id: " + id);
		try {
			Record instance = (Record) getHibernateTemplate().get(
					"com.colin.domain.Record", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Record instance) {
		log.debug("finding Record instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Record instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Record as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByWeixinId(Object weixinId) {
		return findByProperty(WEIXIN_ID, weixinId);
	}

	public List findByRealPlace(Object realPlace) {
		return findByProperty(REAL_PLACE, realPlace);
	}

	public List findByWorkPlace(Object workPlace) {
		return findByProperty(WORK_PLACE, workPlace);
	}

	public List findByEffectiveness(Object effectiveness) {
		return findByProperty(EFFECTIVENESS, effectiveness);
	}

	public List findAll() {
		log.debug("finding all Record instances");
		try {
			String queryString = "from Record";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Record merge(Record detachedInstance) {
		log.debug("merging Record instance");
		try {
			Record result = (Record) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Record instance) {
		log.debug("attaching dirty Record instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Record instance) {
		log.debug("attaching clean Record instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RecordDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RecordDAO) ctx.getBean("RecordDAO");
	}
}