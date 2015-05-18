/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd;

import com.google.common.base.Optional;
import net.emoreira.hfd.model.Hfd;
import org.openide.filesystems.FileObject;

/**
 *
 * @author edson.moreira
 */
public interface FileHandler {
    public Optional<Hfd> readFile(FileObject fileObject);
    public boolean writeFile(Hfd obj, FileObject fileObject);
}
