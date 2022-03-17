package ch.pledarigrond.lucene.config.querybuilder;

import ch.pledarigrond.lucene.config.querybuilder.modifier.*;

public enum BuiltInQueryBuilders {

	DEFAULT(DefaultQueryBuilder.class), EXACT(ExactMatchQueryBuilder.class), INFIX(InfixQueryBuilder.class),
	PREFIX(PrefixQueryBuilder.class), SUFFIX(SuffixQueryBuilder.class);

	private Class<PgQueryBuilder> clazz;

	@SuppressWarnings("unchecked")
	private BuiltInQueryBuilders(Class<?> clazz) {
		this.clazz = (Class<PgQueryBuilder>) clazz;
	}

	public Class<PgQueryBuilder> getClazz() {
		return clazz;
	}
}
