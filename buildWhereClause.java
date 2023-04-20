public static String buildWhereClause(SearchQuery searchQuery) {
    List<SearchQuery.SearchCondition> conditions = searchQuery.getConditions();
    if (conditions == null || conditions.isEmpty()) {
        return "";
    }

    StringBuilder whereClauseBuilder = new StringBuilder();
    boolean firstCondition = true;

    for (SearchQuery.SearchCondition condition : conditions) {
        if (firstCondition) {
            whereClauseBuilder.append("(");
            firstCondition = false;
        } else {
            whereClauseBuilder.append(" ");
            whereClauseBuilder.append(searchQuery.getOperator().toUpperCase());
            whereClauseBuilder.append(" (");
        }

        List<SearchQuery.SearchCondition> subConditions = condition.getConditions();
        if (subConditions != null && !subConditions.isEmpty()) {
            whereClauseBuilder.append(buildWhereClause(new SearchQuery(subConditions, condition.getOperator())));
        } else {
            whereClauseBuilder.append(condition.getColumn_name());
            whereClauseBuilder.append(" ");
            whereClauseBuilder.append(condition.getOperator());
            whereClauseBuilder.append(" ");
            if (condition.getColumn_value() instanceof String) {
                whereClauseBuilder.append("'");
                whereClauseBuilder.append(condition.getColumn_value());
                whereClauseBuilder.append("'");
            } else {
                whereClauseBuilder.append(condition.getColumn_value());
            }
        }

        whereClauseBuilder.append(")");
    }

    return whereClauseBuilder.toString();
}
