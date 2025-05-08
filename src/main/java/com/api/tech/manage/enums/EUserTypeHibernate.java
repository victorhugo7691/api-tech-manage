package com.api.tech.manage.enums;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

//Essa classe foi criada para tratar o tipo de ENUM criado no PostgreSql, o Hibernate não sabe lidar diretamente com ENUMS diferentes do tipo integer, boolean e varchar, essa classe foi criada para traduzir o tipo para o hibernate.
public class EUserTypeHibernate implements UserType<EUserType> {

	@Override
	public int getSqlType() {
		return Types.OTHER;
	}

	@Override
	public Class<EUserType> returnedClass() {
		return EUserType.class;
	}

	@Override
	public boolean equals(EUserType x, EUserType y) {
		return Objects.equals(x, y);
	}

	@Override
	public EUserType nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		String value = rs.getString(position);
		return value != null ? EUserType.valueOf(value) : null;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, EUserType value, int index, SharedSessionContractImplementor session)
			throws SQLException {
		if (value != null) {
			st.setObject(index, value.name(), Types.OTHER);
		} else {
			st.setNull(index, Types.OTHER);
		}
	}

	@Override
	public int hashCode(EUserType x) {
		return Objects.hashCode(x);
	}

	@Override
	public EUserType deepCopy(EUserType value) {
		return value; // enums são imutáveis
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(EUserType value) {
		return value.name();
	}

	@Override
	public EUserType assemble(Serializable cached, Object owner) {
		return EUserType.valueOf((String) cached);
	}

	@Override
	public EUserType replace(EUserType original, EUserType target, Object owner) {
		return original;
	}
}