package buscadordevagas;

import java.util.function.Function;

public enum OpcoesMenu {

    CREATE( s -> {
         return false;
    }),
    SELECT( s -> {
        return false;
    }),
    UPDATE( s -> {
        return false;
    }),
    DELETE( s -> {
        return false;
    });

    final Function<String, Boolean> action;

    OpcoesMenu(Function<String, Boolean> action1) {

        this.action = action1;
    }

    public boolean apply(String operatoin) {
        return this.action.apply(operatoin);
    }

}
