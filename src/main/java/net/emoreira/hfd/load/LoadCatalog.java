/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.load;

import com.google.common.base.Optional;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import net.emoreira.hfd.FileHandler;
import net.emoreira.hfd.model.Architecture;
import net.emoreira.hfd.model.Catalog;
import net.emoreira.hfd.model.Hfd;
import net.emoreira.hfd.xml.XMLFileHandler;
import net.emoreira.hfd.xml.XMLModule;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "net.emoreira.hfd.load.LoadCatalog"
)
@ActionRegistration(
        displayName = "#CTL_LoadCatalog"
)
@ActionReference(path = "Loaders/application/hdf+xml/Actions", position = 250, separatorAfter = 275)
@Messages("CTL_LoadCatalog=Load catalog")
public final class LoadCatalog implements ActionListener {

    private final DataObject context;

    public LoadCatalog(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new LCWizardPanel1());
        String[] steps = new String[panels.size()];
        for (int i = 0; i < panels.size(); i++) {
            Component c = panels.get(i).getComponent();
            // Default step name to component name of panel.
            steps[i] = c.getName();
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
            }
        }
        WizardDescriptor wiz = new WizardDescriptor(new WizardDescriptor.ArrayIterator<WizardDescriptor>(panels));
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wiz.setTitleFormat(new MessageFormat("{0}"));
        wiz.setTitle("Load a Components Catalog");
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            File fo = (File) wiz.getProperty("file");
            FileObject catFile = FileUtil.toFileObject(fo);
            Injector injector = Guice.createInjector(new XMLModule());
            FileHandler handler = injector.getInstance(FileHandler.class);
            Optional<Hfd> originHFD = handler.readFile(catFile);
            if(originHFD.isPresent()){
                Catalog cat = originHFD.get().getCatalog();
                Optional<Hfd> contextHFD = handler.readFile(context.getPrimaryFile());
                if(contextHFD.isPresent()){
                    Hfd hfd = contextHFD.get();
                    hfd.setArchitecture(new Architecture());
                    hfd.setCatalog(cat);
                    handler.writeFile(hfd, context.getPrimaryFile());
                }
            }
        }
    }
}
