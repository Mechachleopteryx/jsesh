package org.qenherkhopeshef.guiFramework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * Very simple facade for building applications menus for one specific window.
 */
public class SimpleApplicationFramework {

	/**
	 * Application default data.
	 */
	private AppDefaults appDefaults = new AppDefaults();

	/**
	 * Actions catalog.
	 */
	private HashMap actionCatalog = new HashMap();

    /**
     * The menu bar (used only for simple applications).
     */
	private JMenuBar jMenuBar;

	private PropertyHolder actionDelegate;

	/**
	 * A basic constructor to build a "raw" application.
	 * All actions and menus must be added afterwards.
	 * @param actionDelegate
	 */
	public SimpleApplicationFramework(PropertyHolder actionDelegate) {
		this.actionDelegate = actionDelegate;
	}

	/**
	 * All in one simple constructor for the action and menu system. 
	 * Once called, you can retrieve the menu bar and the actions.
	 * 
	 * @param ressourceI8n a complete I18n properties ressource path, e.g. org.toto.actionsI18n
	 * @param menuPath the menu definition (a ressource relative to the current path).
	 * @param actionDelegate the class which will be used for the actions.
	 * @throws IOException
	 */
	public SimpleApplicationFramework(String ressourceI8n, String menuPath,
			PropertyHolder actionDelegate) {
		try {
			this.actionDelegate = actionDelegate;
			addRessourceBundle(ressourceI8n);
			addActionList(menuPath);
			buildMenu(menuPath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public SimpleApplicationFramework() {
	}

	/**
	 * Add a I8n resource description to the application. Issues a (written)
	 * warning if it supposes the bundle can't be found. If the path lacks the
	 * package, assume it's in the same place as the delegate.
	 * <p>
	 * <strong>Important</strong> the resource should be in a package.
	 * 
	 * @param path :
	 *            a complete path for I8n resource of the form
	 *            "package.resourceI8n".
	 */
	public void addRessourceBundle(String path) {
		// We try to be as user-friendly as possible.
		if (path.indexOf('.') == -1) {
			path = actionDelegate.getClass().getPackage().getName() + "."
					+ path;
		}
		String filePath = "/" + path.replace('.', '/');
		if (this.getClass().getResource(filePath + ".properties") == null) {
			System.err.println("Bundle " + path
					+ ".properties seems not to be there.");
			System.err
					.println("Make sure you used the full name with packages.");
		}
		appDefaults.addResourceBundle(path);
	}

	/**
	 * Gets the application defaults
	 * 
	 * @return
	 */
	public AppDefaults getAppDefaults() {
		return appDefaults;
	}

	/**
	 * Load a list of action id and build the actions. To avoid unnecessary
	 * work, the format used to describe the action IDs is the same as the one
	 * used to describe the menus.
	 * 
	 * <p>
	 * Note that creating a menu will automatically create the corresponding
	 * actions, so this method will only be called for actions not linked to a
	 * menu.
	 * 
	 * @param actionListRessourceName
	 * @throws IOException
	 */
	public void addActionList(String actionListRessourceName)
			throws IOException {
		InputStream stream = actionDelegate.getClass().getResourceAsStream(
				actionListRessourceName);
		if (stream == null)
			System.err.println("Could not read " + actionListRessourceName);
		addActionList(stream);
	}

	/**
	 * Add a list of actions to create.
	 * <p>
	 * precondition: an action delegate must be set as well as action
	 * descriptions.
	 * 
	 * @param stream
	 * @throws IOException
	 */
	private void addActionList(InputStream stream) throws IOException {
		Reader actionNamesSource = new InputStreamReader(stream, "UTF-8");
		// Use the action factory to build the actions.
		new ActionFactory(actionDelegate, actionCatalog, appDefaults)
				.buildActionsFromText(actionNamesSource);
	}

	/**
	 * Returns the catalog of actions (indexed by action ids).
	 * 
	 * @return
	 */
	public HashMap getActionCatalog() {
		return actionCatalog;
	}

	/**
	 * build a menu according to a description.
	 * 
	 * <p>
	 * Precondition: A resource bundle and an action delegate must be set.
	 * 
	 * @param menuPath
	 * @return
	 * @throws IOException
	 * 
	 */

	public JMenuBar buildMenu(String menuPath) throws IOException {
		// Create the menu.
		MenubarFactory menubarFactory = new MenubarFactory(actionCatalog);
		InputStream menuDescription = actionDelegate.getClass()
				.getResourceAsStream(menuPath);
		if (menuDescription == null)
			System.err.println("Could not read " + menuDescription);
		jMenuBar = menubarFactory.buildMenuBar(new InputStreamReader(
				menuDescription, "UTF-8"));
        getMenu("toto");
		return jMenuBar;
	}

	public JMenuBar getJMenuBar() {
		return jMenuBar;
	}

    /**
     * Returns the menu corresponding to a given result, or null.
     * @param menuName
     * @return
     */
    public JMenu getMenu(String menuName) {
        for (int i=0; i < getJMenuBar().getMenuCount(); i++) {
            JMenu jMenu= getJMenuBar().getMenu(i);
            if (menuName.equals(jMenu.getName()))
                return jMenu;
        }
        return null;
    }

	/**
	 * Build an action map to be used with a custom swing component.
	 * The file containing the list of actions should be in the same
	 *  place as the action delegate class.
	 *  <p> keys ???
	 * @param actionList the name of a file which contains the list of actions to put in the map.
	 * @return 
     * @throws IOException
	 */
	public ActionMap buildActionMap(String actionList) throws IOException {
		ActionMap actionMap = new ActionMap();
		InputStream actionInput = actionDelegate.getClass()
				.getResourceAsStream(actionList);
		if (actionInput == null) {
			System.err.println("Could not read " + actionInput);
			return actionMap;
		}
		BufferedReader r= new BufferedReader(new InputStreamReader(actionInput, "UTF-8"));
		String s;
		while ((s= r.readLine())!= null) {
			s= s.trim();
			final Action action = (Action) actionCatalog.get(s);
			actionMap.put(s, action);
		}
		return actionMap;
	}

	public Action getAction(String string) {
		return (Action) actionCatalog.get(string);
	}

	/**
	 * Sets the action delegate.
	 * 
	 * @param propertyHolder
	 */
	public void setActionDelegate(PropertyHolder propertyHolder) {
		this.actionDelegate = propertyHolder;
	}


}
