package ro.redeul.google.go.inspection;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.ProblemDescriptor;
import ro.redeul.google.go.lang.psi.GoFile;
import ro.redeul.google.go.lang.psi.declarations.GoVarDeclaration;
import ro.redeul.google.go.lang.psi.statements.GoShortVarDeclaration;
import ro.redeul.google.go.lang.psi.visitors.GoRecursiveElementVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarDeclarationInspectionTest extends AbstractProblemDescriptionBasedTest {
    public void testAssignmentCountMismatch() throws Exception{ doTest(); }

    @Override
    protected String getInspectionName() {
        return "varDeclaration";
    }

    @Override
    protected ProblemDescriptor[] detectProblems(GoFile file, final InspectionManager inspectionManager) {
        final List<ProblemDescriptor> problems = new ArrayList<ProblemDescriptor>();
        new GoRecursiveElementVisitor() {
            @Override
            public void visitVarDeclaration(GoVarDeclaration varDeclaration) {
                VarDeclarationInspection inspection = new VarDeclarationInspection(inspectionManager, varDeclaration);
                problems.addAll(Arrays.asList(inspection.checkVar()));
            }

            @Override
            public void visitShortVarDeclaration(GoShortVarDeclaration shortVarDeclaration) {
                visitVarDeclaration(shortVarDeclaration);
            }
        }.visitFile(file);

        return problems.toArray(new ProblemDescriptor[problems.size()]);
    }
}