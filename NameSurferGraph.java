/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants,
		ComponentListener {


    //Variables with the name , color and order of selection
	private HashMap<String, NameSurferEntry> namesGraph = null;//information of names in Graph
	private Color[] colorList = { Color.RED, Color.MAGENTA, Color.GREEN,
			Color.BLACK, Color.DARK_GRAY, Color.PINK, Color.ORANGE,
			Color.CYAN, Color.BLUE };//Colors to use
	private HashMap<String, Color> namesColor = null;//Colors of Labels and Lines
	private int posLastColor;//to rotate the colors used
    private ArrayList<String> namesList = null;//list of names in the graph

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {

		addComponentListener(this);

		namesGraph = new HashMap<String, NameSurferEntry>();
		namesColor = new HashMap<String, Color>();
		posLastColor = 0;// position of last color to use.
        namesList= new ArrayList<String>();

	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {

		int numPoints = namesList.size();

		if (numPoints > 0) {
            String keyDelete;
            keyDelete = namesList.get(numPoints-1);
            namesGraph.remove(keyDelete);
			namesColor.remove(keyDelete);
            namesList.remove(numPoints-1);

		}

		removeAll();
		update();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note
	 * that this method does not actually draw the graph, but simply stores the
	 * entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {

		int maxColors = colorList.length;


		if (namesGraph.containsKey(entry.getName())) {//Name already in Graph

		} else {
            namesList.add(entry.getName());
			namesGraph.put(entry.getName(), entry);
			namesColor.put(entry.getName(), colorList[posLastColor]);
			posLastColor++;
			posLastColor %= maxColors;
		}


		update();

	}

	public int scaleY(int posY) {
		int maxHeight = this.getHeight();
		if (posY == 0) {
			return maxHeight - GRAPH_MARGIN_SIZE;
		}
		int scalar = GRAPH_MARGIN_SIZE
				+ (posY * (maxHeight - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK);
		return scalar;
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of
	 * entries. Your application must call update after calling either clear or
	 * addEntry; update is also called whenever the size of the canvas changes.
	 */
	public void update() {

		this.removeAll();
		int maxHeight = this.getHeight();
		int maxWidth = this.getWidth();


        //Lines and Labels in the empty graphs.
		int incremento = maxWidth / NDECADES;
		for (int i = 0; i < NDECADES; i++) {
			int posX = i * incremento ;

			GLine bodyLine = new GLine(posX, 0, posX, maxHeight);
			add(bodyLine);
			int decade = START_DECADE + i * 10;
			GLabel labelsYears = new GLabel(decade + " ", posX , maxHeight);
			add(labelsYears);

		}
		GLine lineUp = new GLine(0, GRAPH_MARGIN_SIZE, maxWidth, GRAPH_MARGIN_SIZE);
		add(lineUp);
		GLine lineBottom = new GLine(0, maxHeight-GRAPH_MARGIN_SIZE, maxWidth, maxHeight-GRAPH_MARGIN_SIZE);
		add(lineBottom);

		Set<Map.Entry<String, NameSurferEntry>> set = namesGraph.entrySet();

		// Get an iterator
		Iterator<Map.Entry<String, NameSurferEntry>> it = set.iterator();
		// Display elements
		int ctaNames = 0;
		while (it.hasNext()) {
			NameSurferEntry me = it.next().getValue();

			if (me != null) {
				int posX;
				int posYInic;
				Color colorName = namesColor.get(me.getName());
				for (int i = 0; i < NDECADES - 1; i++) {

					posYInic = scaleY(me.getRank(i));
					posX = i * incremento ;
					int posY = scaleY(me.getRank(i + 1));
					GLine bodyLine = new GLine(posX, posYInic, posX
							+ incremento, posY);
					add(bodyLine);

					GLabel labelsYears = new GLabel(me.getName() + " "
							+ me.getRank(i), posX, posYInic);

					labelsYears.setColor(colorName);
					bodyLine.setColor(colorName);
					add(labelsYears);

				}
				posYInic = scaleY(me.getRank(NDECADES-1));
				posX = (NDECADES-1) * incremento;// + 5;
				GLabel labelsYears = new GLabel(me.getName() + " "
						+ me.getRank(NDECADES-1), posX, posYInic);
				labelsYears.setColor(colorName);
				add(labelsYears);
				
				ctaNames++;

			}
		}

	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
