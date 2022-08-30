package my.stone.parser.element;

import my.stone.parser.ast.ASTList;
import my.stone.parser.ast.ASTree;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public abstract class Factory {
    public static final String factoryName = "create";

    protected abstract ASTree make0(Object arg) throws Exception;

    public ASTree make(Object arg) {
        try {
            return make0(arg);
        } catch (IllegalArgumentException e1) {
            throw e1;
        } catch (Exception e2) {
            throw new RuntimeException(e2); // this compiler is broken.
        }
    }

    public static Factory getForASTList(Class<? extends ASTree> clazz) {
        Factory f = get(clazz, List.class);
        if (f == null)
            f = new Factory() {
                protected ASTree make0(Object arg) throws Exception {
                    @SuppressWarnings("unchecked")
                    List<ASTree> results = (List<ASTree>) arg;
                    if (results.size() == 1)
                        return results.get(0);
                    else
                        return new ASTList(results);
                }
            };
        return f;
    }

    public static Factory get(Class<? extends ASTree> clazz,
                              Class<?> argType) {
        if (clazz == null)
            return null;
        try {
            final Method m = clazz.getMethod(factoryName,
                    new Class<?>[]{argType});
            return new Factory() {
                protected ASTree make0(Object arg) throws Exception {
                    return (ASTree) m.invoke(null, arg);
                }
            };
        } catch (NoSuchMethodException e) {
        }
        try {
            final Constructor<? extends ASTree> c
                    = clazz.getConstructor(argType);
            return new Factory() {
                protected ASTree make0(Object arg) throws Exception {
                    return c.newInstance(arg);
                }
            };
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
