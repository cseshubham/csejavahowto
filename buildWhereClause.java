public static String buildWhereClause(SearchQuery searchQuery) {
    List<SearchQuery.SearchCondition> conditions = searchQuery.getConditions();
    if (conditions == null || conditions.isEmpty()) {
        return "";
    }

    StringBuilder whereClauseBuilder = new StringBuilder();
    boolean firstCondition = true;

    for (SearchQuery.SearchCondition condition : conditions) {
        if (firstCondition) {
            firstCondition = false;
        } else {
            whereClauseBuilder.append(" ");
            whereClauseBuilder.append(searchQuery.getConditionalOperator().toUpperCase());
            whereClauseBuilder.append(" ");
        }

        List<SearchQuery.SearchCondition> subConditions = condition.getConditions();
        if (subConditions != null && !subConditions.isEmpty()) {
            whereClauseBuilder.append("(");
            whereClauseBuilder.append(buildWhereClause(new SearchQuery(subConditions, condition.getConditionalOperator())));
            whereClauseBuilder.append(")");
        } else {
            whereClauseBuilder.append(condition.getColumnName());
            whereClauseBuilder.append(" ");
            whereClauseBuilder.append(condition.getOperator());
            whereClauseBuilder.append(" ");
            if (condition.getColumnValue() instanceof String) {
                whereClauseBuilder.append("'");
                whereClauseBuilder.append(condition.getColumnValue());
                whereClauseBuilder.append("'");
            } else {
                whereClauseBuilder.append(condition.getColumnValue());
            }
        }
    }

    return whereClauseBuilder.toString();
}
