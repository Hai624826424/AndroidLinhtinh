package linhtinh.sea.mh.linhtinh.Utility;

import android.hardware.Camera;

import java.util.Iterator;
import java.util.List;

/**
 * Created by WIN-HAIVM on 11/24/17.
 */

public class CameraUtils {

    public static Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int screenWidth/*, int screenHeight*/) {
        Camera.Size optimalSize = null;
        int maxPreview = 0;

        for (Iterator<Camera.Size> iterator = sizes.iterator(); iterator.hasNext(); ) {
            Camera.Size currSize = iterator.next();
            if (maxPreview < currSize.height) {
                maxPreview = currSize.height;
            }

            if (currSize.height == screenWidth) {
                if (optimalSize == null) {
                    optimalSize = currSize;
                }
                if (currSize.width < optimalSize.width) {
                    optimalSize = currSize;
                }
            }
        }
        if (optimalSize == null) {
            for (Iterator<Camera.Size> iterator = sizes.iterator(); iterator.hasNext(); ) {
                Camera.Size currSize = iterator.next();

                if (currSize.height == maxPreview) {
                    if (optimalSize == null) {
                        optimalSize = currSize;
                    }
                    if (currSize.width < optimalSize.width) {
                        optimalSize = currSize;
                    }
                }
            }
        }
        if (optimalSize == null) {
            //did not find a size with the correct aspect ratio.. let's choose the smallest instead
            for (Iterator<Camera.Size> iterator = sizes.iterator(); iterator.hasNext(); ) {
                Camera.Size currSize = iterator.next();
                if (optimalSize != null) {
                    //is the current size smaller than the one before
                    if (optimalSize.height > currSize.height && optimalSize.width > currSize.width) {
                        optimalSize = currSize;
                    } else {
                        optimalSize = currSize;
                    }
                } else {
                    optimalSize = currSize;
                }

            }
        }
        return optimalSize;
    }

    public static boolean containsSize(List<Camera.Size> sizes, Camera.Size size) {
        for (Iterator<Camera.Size> iterator = sizes.iterator(); iterator.hasNext(); ) {
            Camera.Size currSize = iterator.next();
            if (currSize.width == size.width && currSize.height == size.height) {
                return true;
            }
        }
        return false;
    }

    public static Camera.Size getSmallestSize(List<Camera.Size> sizes) {
        Camera.Size optimalSize = null;
        for (Iterator<Camera.Size> iterator = sizes.iterator(); iterator.hasNext(); ) {
            Camera.Size currSize = iterator.next();
            if (optimalSize == null) {
                optimalSize = currSize;
            } else if (optimalSize.height > currSize.height && optimalSize.width > currSize.width) {
                optimalSize = currSize;
            }
        }
        return optimalSize;
    }
}
