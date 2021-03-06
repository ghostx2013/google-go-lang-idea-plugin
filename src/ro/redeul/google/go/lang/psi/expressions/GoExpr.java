package ro.redeul.google.go.lang.psi.expressions;

import org.jetbrains.annotations.NotNull;
import ro.redeul.google.go.lang.psi.GoPsiElement;
import ro.redeul.google.go.lang.psi.typing.GoType;
import ro.redeul.google.go.lang.psi.typing.GoTypes;

/**
 * GoPsi interface for all the Psi nodes that are expressions.
 */
public interface GoExpr extends GoPsiElement {

    public final GoExpr[] EMPTY_ARRAY = new GoExpr[0];

    @NotNull
    GoType[] getType();

    boolean isConstantExpression();

    boolean hasType(GoTypes.Builtin builtinType);

    boolean hasType(GoType type);
}
