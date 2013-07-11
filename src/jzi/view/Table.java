package jzi.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jzi.model.entities.IPlayer;
import jzi.model.game.IGame;

/**
 * 
 * @author Tobias Groth
 * 
 */
public class Table extends JTable {
	/**
	 * UID serilization.
	 */
	private static final long serialVersionUID = 8196416335830121975L;
	/**
	 * Header of the Table.
	 */
	private JTableHeader header;
	/**
	 * scaled ammo image for the table.
	 */
	private Image scaleAmmoImage;
	/**
	 * scaled life image for the table.
	 */
	private Image scaleLifeImage;
	/**
	 * scaled zombie image for the table.
	 */
	private Image scaleZombieImage;
	/**
	 * current game instance.
	 */
	private IGame game;

	/**
	 * create a new Table.
	 * 
	 * @param dtm
	 * @param ammoImage
	 * @param lifeImage
	 * @param zombieImage
	 * @param game
	 */
	public Table(DefaultTableModel dtm, BufferedImage ammoImage,
			BufferedImage lifeImage, BufferedImage zombieImage, IGame game) {
		super(dtm);
		this.game = game;
		setDefaultRenderer(Object.class, new ColorTableCellRenderer());
		header = getTableHeader();
		header.setReorderingAllowed(false);
		header.setDefaultRenderer(new ImageHeaderCellRenderer());

		scaleAmmoImage = ammoImage.getScaledInstance(30, 30, 0);
		scaleLifeImage = lifeImage.getScaledInstance(30, 30, 0);
		scaleZombieImage = zombieImage.getScaledInstance(30, 30, 0);

	}

	/**
	 * set Data in a row.
	 * 
	 * @param row
	 * @param player
	 */
	public final void setRow(int row, IPlayer player) {
		setValueAt(player.getName(), row, 0);
		setValueAt(player.getColor(), row, 1);
		setValueAt(player.getPoints(), row, 2);
		setValueAt(player.getLives(), row, 3);
		setValueAt(player.getAmmo(), row, 4);
		if (game.isHardcore()) {
			setValueAt(game.getRevives() - player.getRevives(), row, 5);
		}
	}

	/**
	 * change the language of the header.
	 */
	public final void changeLanguage() {
		JTableHeader th = this.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		TableColumn tc2 = tcm.getColumn(1);

		tc.setHeaderValue(Language.get("game.player.name"));
		tc2.setHeaderValue(Language.get("game.player.color"));

		if (game.isHardcore()) {
			TableColumn tc3 = tcm.getColumn(5);
			tc3.setHeaderValue(Language.get("game.player.revives"));

		}

		th.repaint();
	}

	/**
	 * Renderer for the Data of the Table.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class ColorTableCellRenderer extends DefaultTableCellRenderer {

		/**
		 * serial ID.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Override setValue from DefaultTableCellRenderer.
		 */
		@Override
		protected void setValue(Object value) {

			if (value instanceof Color) {
				setOpaque(true);
				setBackground((Color) value);
				setText("");
			} else {
				if (value == null) {
					setText("");
				} else {
					setText(value.toString());
				}
				setOpaque(false);

			}
		}

	}

	/**
	 * Renderer for the header of the Table.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class ImageHeaderCellRenderer extends JLabel implements
			TableCellRenderer {
		/**
		 * serial ID.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Paint Images in Column 2,3,4 This Method has 6 Parameters, because
		 * the java interface definite his method with 6 Parameters.
		 */
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			setBorder(UIManager.getBorder("TableHeader.cellBorder"));

			if (column == 4) {

				setIcon(new ImageIcon(scaleAmmoImage));
				setPreferredSize(new Dimension(30, 30));
				setOpaque(true);
				setText("");
			} else {
				if (column == 3) {

					setIcon(new ImageIcon(scaleLifeImage));
					setPreferredSize(new Dimension(30, 30));
					setOpaque(true);
					setText("");
				} else {
					if (column == 2) {

						setIcon(new ImageIcon(scaleZombieImage));
						setPreferredSize(new Dimension(30, 30));
						setOpaque(true);
						setText("");
					} else {
						setIcon(null);
						setOpaque(false);
						setPreferredSize(new Dimension(100, 30));
						setText(value.toString());
					}
				}
			}

			return this;
		}
	}

}
