/*
 * Copyright ou © ou Copr. Serge Rosmorduc (2016) 
 * serge.rosmorduc@cnam.fr

 * Ce logiciel est un programme informatique servant à mettre en place 
 * une base de données linguistique pour l'égyptien ancien.

 * Ce logiciel est régi par la licence CeCILL soumise au droit français et
 * respectant les principes de diffusion des logiciels libres. Vous pouvez
 * utiliser, modifier et/ou redistribuer ce programme sous les conditions
 * de la licence [CeCILL|CeCILL-B|CeCILL-C] telle que diffusée par le CEA, le CNRS et l'INRIA 
 * sur le site "http://www.cecill.info".

 * En contrepartie de l'accessibilité au code source et des droits de copie,
 * de modification et de redistribution accordés par cette licence, il n'est
 * offert aux utilisateurs qu'une garantie limitée.  Pour les mêmes raisons,
 * seule une responsabilité restreinte pèse sur l'auteur du programme,  le
 * titulaire des droits patrimoniaux et les concédants successifs.

 * A cet égard  l'attention de l'utilisateur est attirée sur les risques
 * associés au chargement,  à l'utilisation,  à la modification et/ou au
 * développement et à la reproduction du logiciel par l'utilisateur étant 
 * donné sa spécificité de logiciel libre, qui peut le rendre complexe à 
 * manipuler et qui le réserve donc à des développeurs et des professionnels
 * avertis possédant  des  connaissances  informatiques approfondies.  Les
 * utilisateurs sont donc invités à charger  et  tester  l'adéquation  du
 * logiciel à leurs besoins dans des conditions permettant d'assurer la
 * sécurité de leurs systèmes et ou de leurs données et, plus généralement, 
 * à l'utiliser et l'exploiter dans les mêmes conditions de sécurité. 

 * Le fait que vous puissiez accéder à cet en-tête signifie que vous avez 
 * pris connaissance de la licence CeCILL, et que vous en avez accepté les
 * termes.
 */
package jsesh.jhotdraw.actions.edit;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import jsesh.editor.MdCSearchQuery;
import jsesh.jhotdraw.actions.BundleHelper;
import jsesh.jhotdraw.viewClass.JSeshView;
import jsesh.search.ui.JWildcardPanel;
import jsesh.search.clientApi.SearchTarget;
import jsesh.search.ui.SearchPanelFactory;
import org.jhotdraw_7_6.app.Application;
import org.jhotdraw_7_6.app.View;
import org.jhotdraw_7_6.app.action.AbstractApplicationAction;

/**
 * Find an element action.
 *
 * Simple search functionality for JSesh.
 *
 * @author rosmord
 */
public final class FindAction extends AbstractApplicationAction {

    public static final String ID = "edit.find";

    private final JFrame frame;
    private final JWildcardPanel searchPanel;
    private final SearchTarget searchAdapter = new MyTarget();

    public FindAction(Application app) {
        super(app);
        searchPanel = SearchPanelFactory.createWildCardPanel(searchAdapter);
        frame = new JFrame();
        frame.add(searchPanel);
        frame.pack();
        BundleHelper.getInstance().configure(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (searchAdapter.isAvailable()) {
            frame.setVisible(true);
        }
    }

    private class MyTarget implements SearchTarget {

        @Override
        public boolean isAvailable() {
            View view = getApplication().getActiveView();
            return view != null;
        }

        private JSeshView getJSeshView() {
            return (JSeshView) getApplication().getActiveView();
        }

        @Override
        public void doSearch(MdCSearchQuery query) {
            if (isAvailable()) {
                JSeshView view = getJSeshView();
                view.doSearch(query);
            }
        }

        @Override
        public void nextSearch() {
            if (isAvailable()) {
                getJSeshView().nextSearch();
            }
        }
    }
}
