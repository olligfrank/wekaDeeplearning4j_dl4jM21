/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 *    DefaultInstanceIterator.java
 *    Copyright (C) 2016 University of Waikato, Hamilton, New Zealand
 *
 */
package weka.dl4j.iterators.instance;

import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import weka.classifiers.functions.dl4j.Utils;
import weka.core.Instances;
import weka.core.InvalidInputDataException;
import weka.core.OptionMetadata;
import weka.dl4j.iterators.dataset.DefaultDataSetIterator;

/**
 * Converts the given Instances object into a DataSet and then constructs and returns a
 * DefaultDataSetIterator.
 *
 * @author Christopher Beckham
 * @author Eibe Frank
 * @author Steven Lang
 */
public class DefaultInstanceIterator extends AbstractInstanceIterator {

  /** The ID used to serialize this class */
  private static final long serialVersionUID = 1316260988724548474L;

  @Override
  public void validate(Instances data) throws InvalidInputDataException {
    if (data.classIndex() < 0){
      throw new InvalidInputDataException("Class index not set.");
    }
  }


  /**
   * Returns the actual iterator.
   *
   * @param data the dataset to use
   * @param seed the seed for the random number generator
   * @param batchSize the batch size to use
   * @return the DataSetIterator
   */
  @Override
  public DataSetIterator getDataSetIterator(Instances data, int seed, int batchSize)
      throws InvalidInputDataException {
    validate(data);
    // Convert Instances to DataSet
    DataSet dataset = Utils.instancesToDataSet(data);
    return new DefaultDataSetIterator(dataset, batchSize);
  }

  public String globalInfo() {
    return "Instance iterator reads rows from the given ARFF file. This "
        + "iterator is not compatible with convolution layers. See also: "
        + "ConvolutionInstanceIterator/ImageInstanceIterator.";
  }
}
