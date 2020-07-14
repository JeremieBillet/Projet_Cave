package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.intiformation.projet.caveavin.modele.Alcool;
import com.intiformation.projet.caveavin.modele.Biere;
import com.intiformation.projet.caveavin.modele.CatCepage;
import com.intiformation.projet.caveavin.modele.CatPays;
import com.intiformation.projet.caveavin.modele.CatRegion;
import com.intiformation.projet.caveavin.modele.CatType;
import com.intiformation.projet.caveavin.modele.Champagne;
import com.intiformation.projet.caveavin.modele.Spiritueux;
import com.intiformation.projet.caveavin.modele.Vin;

public class AlcoolDAOImpl implements IAlcoolDAO {


	@Override
	public boolean add(Alcool pAlcool) {

		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		
		ResultSet rs2 = null;
		
		try {
			ps1 = this.connection.prepareStatement("INSERT INTO alcools (class_name, designation, description, prix, quantité, photo, annee) VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			ps1.setString(1, pAlcool.getClass().getSimpleName());
			ps1.setString(2, pAlcool.getDesignation());
			ps1.setString(3, pAlcool.getDescription());
			ps1.setDouble(4, pAlcool.getPrix());
			ps1.setInt(5, pAlcool.getQuantite());
			ps1.setString(6, pAlcool.getPhoto());
			if (pAlcool instanceof Vin) {
				ps1.setInt(7, ((Vin)pAlcool).getAnnee());
			} else if (pAlcool instanceof Champagne) {
				ps1.setInt(7, ((Champagne)pAlcool).getAnnee());
			} else {
				ps1.setInt(7, 0);
			}
			
			
			ps1.executeUpdate();
			
			ps2 = this.connection.prepareStatement("SELECT MAX(id_alcool) FROM alcools");
			
			rs2 = ps2.executeQuery();
			
			rs2.next();
			
			int idAlcool = rs2.getInt(1);
			
			if (pAlcool instanceof Biere) {
				ps3 = this.connection.prepareStatement("INSERT INTO alcools_categories VALUES (?,?), (?,?)");
				
				ps3.setInt(1, idAlcool);
				ps3.setInt(2, ((Biere) pAlcool).getPays().getIdCategorie());
				ps3.setInt(3, idAlcool);
				ps3.setInt(4, ((Biere) pAlcool).getType().getIdCategorie());
				
			} else if (pAlcool instanceof Spiritueux) {
				ps3 = this.connection.prepareStatement("INSERT INTO alcools_categories VALUES (?,?), (?,?)");
				
				ps3.setInt(1, idAlcool);
				ps3.setInt(2, ((Spiritueux) pAlcool).getPays().getIdCategorie());
				ps3.setInt(3, idAlcool);
				ps3.setInt(4, ((Spiritueux) pAlcool).getType().getIdCategorie());
				
			} else if (pAlcool instanceof Vin) {
				ps3 = this.connection.prepareStatement("INSERT INTO alcools_categories VALUES (?,?), (?,?), (?,?)");
				
				ps3.setInt(1, idAlcool);
				ps3.setInt(2, ((Vin) pAlcool).getPays().getIdCategorie());
				ps3.setInt(3, idAlcool);
				ps3.setInt(4, ((Vin) pAlcool).getRegion().getIdCategorie());
				ps3.setInt(5, idAlcool);
				ps3.setInt(6, ((Vin) pAlcool).getType().getIdCategorie());
				
				addCepageAlcool(idAlcool, ((Vin) pAlcool).getCepage());
				
			} else if (pAlcool instanceof Champagne) {
				ps3 = this.connection.prepareStatement("INSERT INTO alcools_categories VALUES (?,?), (?, ?)");
				
				ps3.setInt(1, idAlcool);
				ps3.setInt(2, ((Champagne) pAlcool).getType().getIdCategorie());
				
				addCepageAlcool(idAlcool, ((Champagne) pAlcool).getCepage());
				
			}
			
			int verifUpdate = ps3.executeUpdate();
			
			return (verifUpdate == 1);
			
			
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de l'ajout / méthode add() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (ps1 != null) {
					ps1.close();
				}
				
				if (ps2 != null) {
					ps2.close();
				}
				
				if (ps3 != null) {
					ps3.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return false;
	}//end add()

	@Override
	public boolean update(Alcool pAlcool) {

		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		
		ResultSet rs2 = null;
		
		try {
			ps1 = this.connection.prepareStatement("UPDATE alcools SET class_name=?, designation=?, description=?, prix=?, quantité=?, photo=?, annee=? WHERE id_alcool=?");
			
			int idAlcool = pAlcool.getIdAlcool();
			
			ps1.setString(1, pAlcool.getClass().getName());
			ps1.setString(2, pAlcool.getDesignation());
			ps1.setString(3, pAlcool.getDescription());
			ps1.setDouble(4, pAlcool.getPrix());
			ps1.setInt(5, pAlcool.getQuantite());
			ps1.setString(6, pAlcool.getPhoto());
			if (pAlcool instanceof Vin) {
				ps1.setInt(6, ((Vin) pAlcool).getAnnee());
			} else if (pAlcool instanceof Champagne) {
				ps1.setInt(6, ((Champagne) pAlcool).getAnnee());
			} else {
				ps1.setInt(6, 0);
			}
			ps1.setInt(1, idAlcool);
			
			ps1.executeUpdate();
			
			ps2 = this.connection.prepareStatement("DELETE FROM alcools_categories WHERE alcool_id=?");
			
			ps2.setInt(1, idAlcool);
			
			ps2.executeUpdate();
			
			if (pAlcool instanceof Biere) {
				ps3 = this.connection.prepareStatement("INSERT INTO alcools_categories VALUES (?,?), (?,?)");
				
				ps3.setInt(1, idAlcool);
				ps3.setInt(2, ((Biere) pAlcool).getPays().getIdCategorie());
				ps3.setInt(3, idAlcool);
				ps3.setInt(4, ((Biere) pAlcool).getType().getIdCategorie());
				
			} else if (pAlcool instanceof Spiritueux) {
				ps3 = this.connection.prepareStatement("INSERT INTO alcools_categories VALUES (?,?), (?,?)");
				
				ps3.setInt(1, idAlcool);
				ps3.setInt(2, ((Spiritueux) pAlcool).getPays().getIdCategorie());
				ps3.setInt(3, idAlcool);
				ps3.setInt(4, ((Spiritueux) pAlcool).getType().getIdCategorie());
				
			} else if (pAlcool instanceof Vin) {
				ps3 = this.connection.prepareStatement("INSERT INTO alcools_categories VALUES (?,?), (?,?), (?,?)");
				
				ps3.setInt(1, idAlcool);
				ps3.setInt(2, ((Vin) pAlcool).getPays().getIdCategorie());
				ps3.setInt(3, idAlcool);
				ps3.setInt(4, ((Vin) pAlcool).getRegion().getIdCategorie());
				ps3.setInt(5, idAlcool);
				ps3.setInt(6, ((Vin) pAlcool).getType().getIdCategorie());
				
				addCepageAlcool(idAlcool, ((Vin) pAlcool).getCepage());
				
			} else if (pAlcool instanceof Champagne) {
				ps3 = this.connection.prepareStatement("INSERT INTO alcools_categories VALUES (?,?)");
				
				ps3.setInt(1, idAlcool);
				ps3.setInt(2, ((Champagne) pAlcool).getType().getIdCategorie());
				
				addCepageAlcool(idAlcool, ((Champagne) pAlcool).getCepage());
				
			}
			
			int verifUpdate = ps3.executeUpdate();
			
			return (verifUpdate == 1);
			
			
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la modification / méthode update() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (ps1 != null) {
					ps1.close();
				}
				
				if (ps2 != null) {
					ps2.close();
				}
				
				if (ps3 != null) {
					ps3.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return false;
	}//end update()

	@Override
	public boolean delete(Integer pIdAlcool) {

		PreparedStatement ps = null;
		
		try {
			
			ps = this.connection.prepareStatement("DELETE FROM alcools WHERE id_alcool=?");
			
			ps.setInt(1, pIdAlcool);
			
			int verifSuppr = ps.executeUpdate();
			
			return (verifSuppr == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la suppression / méthode delete() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally block
		
		return false;
	}//end delete

	@Override
	public Alcool getById(Integer pIdAlcool) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM alcools WHERE id_alcool=?");
			ps.setInt(1, pIdAlcool);
			
			
			ps2 = this.connection.prepareStatement("SELECT * FROM categories INNER JOIN alcools_categories ON id_categorie=categorie_id WHERE alcool_id=?");
			ps2.setInt(1, pIdAlcool);
			
			rs2 = ps2.executeQuery();
			
			CatPays pays = null;
			CatType type = null;
			CatRegion region = null;
			List<CatCepage> listeCepages = new ArrayList<>();
			
			
			while (rs2.next()) {
				
				if (rs2.getString(2).equals("CatPays")) {
					pays = new CatPays(rs2.getInt(1), rs2.getString(3));
				}
				if (rs2.getString(2).equals("CatRegion")) {
					region = new CatRegion(rs2.getInt(1), rs2.getString(3));
				}
				if (rs2.getString(2).equals("CatType")) {
					type = new CatType(rs2.getInt(1), rs2.getString(3), rs2.getString(4));
				}
				if (rs2.getString(2).equals("CatCepage")) {
					listeCepages.add(new CatCepage(rs2.getInt(1), rs2.getString(3)));
				}
			}//end while - commun
			
			rs = ps.executeQuery();
			rs.next();
			
			int id = rs.getInt(1);
			String classe = rs.getString(2);
			String nom = rs.getString(3);
			String description = rs.getString(4);
			int prix = rs.getInt(5);
			int quantite = rs.getInt(6);
			boolean selectionne = rs.getBoolean(7);
			String photo = rs.getString(8);
			double promo = rs.getDouble(9);
			//fin attributs communs
			
			if (rs.getString(2).equals("Biere")) {
				
				return new Biere(id, classe, nom, description, prix, quantite, selectionne, photo, promo, pays, type);
				
			} /*end if biere*/ else if (rs.getString(2).equals("Spiritueux")) {
				
				return new Spiritueux(id, classe, nom, description, prix, quantite, selectionne, photo, promo, pays, type);
				
			} /*end if spiritueux*/ else if (rs.getString(2).equals("Champagne")) {
				
				int annee = rs.getInt(10);
				
				return new Champagne(id, classe, nom, description, prix, quantite, selectionne, photo, promo, type, listeCepages, annee);
				
			} /*end if champagne*/ else if (rs.getString(2).equals("Vin")) {
				System.out.println("ok8");
				int annee = rs.getInt(10);
				
				return new Vin(id, classe, nom, description, prix, quantite, selectionne, photo, promo, pays, region, type, listeCepages, annee);
				
			} //end if vin
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération par Id / méthode getById() ...");
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
				if (ps2 != null) {
					ps2.close();
				}
				
				if (rs2 != null) {
					rs2.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}
		
		return null;
	}//end getById()

	@Override
	public Alcool getByNom(String pNomAlcool) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM alcools WHERE designation=?");
			
			ps.setString(1, pNomAlcool);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			int id = rs.getInt(1);
			
			ps2 = this.connection.prepareStatement("SELECT * FROM categories INNER JOIN alcools_categories ON id_categorie=categorie_id WHERE alcool_id=?");
			
			ps2.setInt(1, id);
			
			rs2 = ps2.executeQuery();
			
			CatPays pays = null;
			CatType type = null;
			CatRegion region = null;
			List<CatCepage> listeCepages = new ArrayList<>();
			
			while (rs2.next()) {
				if (rs2.getString(2).equals("CatPays")) {
					pays = new CatPays(rs2.getInt(1), rs2.getString(3));
				}
				if (rs2.getString(2).equals("CatRegion")) {
					region = new CatRegion(rs2.getInt(1), rs2.getString(3));
				}
				if (rs2.getString(2).equals("CatType")) {
					type = new CatType(rs2.getInt(1), rs2.getString(3), rs2.getString(4));
				}
				if (rs2.getString(2).equals("CatCepage")) {
					listeCepages.add(new CatCepage(rs2.getInt(1), rs2.getString(3)));
				}
			}//end while - commun
			
			String classe = rs.getString(2);
			String nom = rs.getString(3);
			String description = rs.getString(4);
			int prix = rs.getInt(5);
			int quantite = rs.getInt(6);
			boolean selectionne = rs.getBoolean(7);
			String photo = rs.getString(8);
			double promo = rs.getDouble(9);
			
			//fin attributs communs
			
			if (rs.getString(2).equals("Biere")) {
				
				return new Biere(id, classe, nom, description, prix, quantite, selectionne, photo, promo, pays, type);
				
			} /*end if biere*/ else if (rs.getString(2).equals("Spiritueux")) {
				
				return new Spiritueux(id, classe, nom, description, prix, quantite, selectionne, photo, promo, pays, type);
				
			} /*end if spiritueux*/ else if (rs.getString(2).equals("Champagne")) {
				
				int annee = rs.getInt(10);
				
				return new Champagne(id, classe, nom, description, prix, quantite, selectionne, photo, promo, type, listeCepages, annee);
				
			} /*end if champagne*/ else if (rs.getString(2).equals("Vin")) {
				
				int annee = rs.getInt(10);
				
				return new Vin(id, classe, nom, description, prix, quantite, selectionne, photo, promo, pays, region, type, listeCepages, annee);
				
			} //end if vin
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération par Id / méthode getById() ...");
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
				if (ps2 != null) {
					ps2.close();
				}
				
				if (rs2 != null) {
					rs2.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}
		
		return null;
	}//end getByNom()

	@Override
	public List<CatCepage> getAllCepageByAlcool(Integer pIdAlcool) {
		
		PreparedStatement preSta = null;
		ResultSet resSet = null;
		
		try {
			
			preSta = this.connection.prepareStatement("SELECT * FROM alcools_categories, categories WHERE alcool_id=? AND categorie_id=id_categorie AND class_name='CatCepage'");
			
			preSta.setInt(1, pIdAlcool);
			
			resSet = preSta.executeQuery();
			
			CatCepage cepage = null;
			List<CatCepage> listeCepages = new ArrayList<>();
			
			while (resSet.next()) {
				
				cepage = new CatCepage(resSet.getInt(2), resSet.getString(5));
				
				listeCepages.add(cepage);
				
			}//end while
			return listeCepages;
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la récupération de la liste des cépages / méthode getAllCepageByAlcool() ...");
			
			e.printStackTrace();
		} finally {
			
			try {
				if (preSta != null) {
					preSta.close();
				}
				if (resSet != null) {
					resSet.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;
	}//end getAllCepageByAlcool()

	@Override
	public boolean addCepageAlcool(Integer pIdAlcool, List<CatCepage> pListeCepages) {
		
		PreparedStatement ps = null;
		
		try {
			
			for (CatCepage cepageI : pListeCepages) {
				
				ps = this.connection.prepareStatement("INSERT INTO alcools_categories (alcool_id,categorie_id) VALUES (?, ?)");
				
				ps.setInt(1, pIdAlcool);
				ps.setInt(2, cepageI.getIdCategorie());
				
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de l'ajout du cépage / addCepageAlcool() ...");
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}//end addCepageAlcool()

	@Override
	public List<Vin> getAllVin() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM alcools WHERE class_name='Vin'");
			ps2 = this.connection.prepareStatement("SELECT * FROM categories INNER JOIN alcools_categories ON id_categorie=categorie_id WHERE alcool_id=?");
			
			rs = ps.executeQuery();
			
			List<Vin> listeVins = new ArrayList<>();
			
			while (rs.next()) {
				
				int id = rs.getInt(1);
				String classe = rs.getString(2);
				String nom = rs.getString(3);
				String description = rs.getString(4);
				int prix = rs.getInt(5);
				int quantite = rs.getInt(6);
				boolean selectionne = rs.getBoolean(7);
				String photo = rs.getString(8);
				double promo = rs.getDouble(9);
				int annee = rs.getInt(10);
				
				CatPays pays = null;
				CatType type = null;
				CatRegion region = null;
				List<CatCepage> listeCepages = new ArrayList<>();
				
				ps2.setInt(1, id);
				rs2 = ps2.executeQuery();
				
				while (rs2.next()) {
					
					if (rs2.getString(2).equals("CatPays")) {
						pays = new CatPays(rs2.getInt(1), rs2.getString(3));
					}
					if (rs2.getString(2).equals("CatRegion")) {
						region = new CatRegion(rs2.getInt(1), rs2.getString(3));
					}
					if (rs2.getString(2).equals("CatType")) {
						type = new CatType(rs2.getInt(1), rs2.getString(3), rs2.getString(4));
					}
					if (rs2.getString(2).equals("CatCepage")) {
						listeCepages.add(new CatCepage(rs2.getInt(1), rs2.getString(3)));
					}
					
				}//end while - rs2
				
				listeVins.add(new Vin(id, classe, nom, description, prix, quantite, selectionne, photo, promo, pays, region, type, listeCepages, annee));
			}//end while rs
			
			
			return listeVins;
			
		} catch (SQLException e) {

			System.out.println("... Erreur lors de la récupération des vins / getAllVin() ...");
			
			e.printStackTrace();
			
		} finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
				if (ps2 != null) {
					ps2.close();
				}
				
				if (rs2 != null) {
					rs2.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}//end finally
		
		return null;
	}//end getAllVin()

	@Override
	public List<Champagne> getAllChampagne() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM alcools WHERE class_name='Champagne'");
			ps2 = this.connection.prepareStatement("SELECT * FROM categories INNER JOIN alcools_categories ON id_categorie=categorie_id WHERE alcool_id=?");
			
			rs = ps.executeQuery();
			
			Champagne champagne = null;
			List<Champagne> listeChampagnes = null;
			
			CatType type = null;
			List<CatCepage> listeCepages = new ArrayList<>();
			
			while (rs.next()) {
				
				int id = rs.getInt(1);
				String classe = rs.getString(2);
				String nom = rs.getString(3);
				String description = rs.getString(4);
				int prix = rs.getInt(5);
				int quantite = rs.getInt(6);
				boolean selectionne = rs.getBoolean(7);
				String photo = rs.getString(8);
				double promo = rs.getDouble(9);
				int annee = rs.getInt(10);
				
				ps2.setInt(1, id);
				rs2 = ps2.executeQuery();
				
				while (rs2.next()) {
					
					if (rs2.getString(2).equals("CatType")){
						type = new CatType(rs2.getInt(1), rs2.getString(3), rs2.getString(4));
					}
					if (rs2.getString(2).equals("CatCepage")) {
						listeCepages.add(new CatCepage(rs2.getInt(1), rs2.getString(3)));
					}
					
				}//end while - rs2
				
				champagne = new Champagne(id, classe, nom, description, prix, quantite, selectionne, photo, promo, type, listeCepages, annee);
				
				listeChampagnes.add(champagne);
				
			}//end while rs
			
			return listeChampagnes;
			
		} catch (SQLException e) {

			System.out.println("... Erreur lors de la récupération des champagnes / getAllChampagne() ...");
			
			e.printStackTrace();
			
		} finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
				if (ps2 != null) {
					ps2.close();
				}
				
				if (rs2 != null) {
					rs2.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}//end finally
		
		return null;
	}//end getAllChampagne()

	@Override
	public List<Biere> getAllBiere() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM alcools WHERE class_name='Biere'");
			ps2 = this.connection.prepareStatement("SELECT * FROM categories INNER JOIN alcools_categories ON id_categorie=categorie_id WHERE alcool_id=?");
			
			rs = ps.executeQuery();
			
			Biere biere = null;
			List<Biere> listeBieres = null;
			
			CatPays pays = null;
			CatType type = null;
			
			while (rs.next()) {
				
				int id = rs.getInt(1);
				String classe = rs.getString(2);
				String nom = rs.getString(3);
				String description = rs.getString(4);
				int prix = rs.getInt(5);
				int quantite = rs.getInt(6);
				boolean selectionne = rs.getBoolean(7);
				String photo = rs.getString(8);
				double promo = rs.getDouble(9);
				
				ps2.setInt(1, id);
				rs2 = ps2.executeQuery();
				
				while (rs2.next()) {
					
					if (rs2.getString(2).equals("CatPays")) {
						pays = new CatPays(rs2.getInt(1), rs2.getString(3));
					}
					if (rs2.getString(2).equals("CatType")) {
						type = new CatType(rs2.getInt(1), rs2.getString(3), rs2.getString(4));
					}
					
				}//end while - rs2
				
				biere = new Biere(id, classe, nom, description, prix, quantite, selectionne, photo, promo, pays, type);
				
				listeBieres.add(biere);
				
			}//end while rs
			
			return listeBieres;
			
		} catch (SQLException e) {

			System.out.println("... Erreur lors de la récupération des bières / getAllBiere() ...");
			
			e.printStackTrace();
			
		} finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
				if (ps2 != null) {
					ps2.close();
				}
				
				if (rs2 != null) {
					rs2.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}//end finally
		
		return null;
	}//end getAllBiere()

	@Override
	public List<Spiritueux> getAllSpiritueux() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM alcools WHERE class_name='Spiritueux'");
			ps2 = this.connection.prepareStatement("SELECT * FROM categories INNER JOIN alcools_categories ON id_categorie=categorie_id WHERE alcool_id=?");
			
			rs = ps.executeQuery();
			
			Spiritueux spiritueux = null;
			List<Spiritueux> listeSpiritueux = null;
			
			CatPays pays = null;
			CatType type = null;
			
			while (rs.next()) {
				
				int id = rs.getInt(1);
				String classe = rs.getString(2);
				String nom = rs.getString(3);
				String description = rs.getString(4);
				int prix = rs.getInt(5);
				int quantite = rs.getInt(6);
				boolean selectionne = rs.getBoolean(7);
				String photo = rs.getString(8);
				double promo = rs.getDouble(9);
				
				ps2.setInt(1, id);
				rs2 = ps2.executeQuery();
				
				while (rs2.next()) {
					
					if (rs2.getString(2).equals("CatPays")) {
						pays = new CatPays(rs2.getInt(1), rs2.getString(3));
					}
					if (rs2.getString(2).equals("CatType")) {
						type = new CatType(rs2.getInt(1), rs2.getString(3), rs2.getString(4));
					}
					
				}//end while - rs2
				
				spiritueux = new Spiritueux(id, classe, nom, description, prix, quantite, selectionne, photo, promo, pays, type);
				
				listeSpiritueux.add(spiritueux);
				
			}//end while rs
			
			return listeSpiritueux;
			
		} catch (SQLException e) {

			System.out.println("... Erreur lors de la récupération des spiritueux / getAllSpiritueux() ...");
			
			e.printStackTrace();
			
		} finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
				if (ps2 != null) {
					ps2.close();
				}
				
				if (rs2 != null) {
					rs2.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}//end finally
		
		return null;
	}//end getAllSpiritueux()

}//end class
