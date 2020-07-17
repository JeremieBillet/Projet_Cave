package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.Champagne;

public class ChampagneDAOImpl implements IChampagneDAO {

	@Override
	public boolean add(Champagne pChampagne) {

		PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pChampagne.getDesignation());
			ps.setString(2, pChampagne.getDescription());
			ps.setDouble(3, pChampagne.getPrix());
			ps.setInt(4, pChampagne.getQuantite());
			ps.setBytes(5, pChampagne.getPhoto());
			ps.setInt(6, pChampagne.getIdType());
			ps.setInt(7, pChampagne.getIdCepage());
			ps.setInt(8, pChampagne.getAnnee());
			
			int verifUpdate = ps.executeUpdate();
			
			return (verifUpdate == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de l'ajout du champagne / méthode add() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return false;
	}//end add() - champagne

	@Override
	public boolean update(Champagne pChampagne) {

		PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pChampagne.getDesignation());
			ps.setString(2, pChampagne.getDescription());
			ps.setDouble(3, pChampagne.getPrix());
			ps.setInt(4, pChampagne.getQuantite());
			ps.setBytes(5, pChampagne.getPhoto());
			ps.setInt(6, pChampagne.getIdType());
			ps.setInt(7, pChampagne.getIdCepage());
			ps.setInt(8, pChampagne.getAnnee());
			ps.setInt(9, pChampagne.getIdProduit());
			
			int verifUpdate = ps.executeUpdate();
			
			return (verifUpdate == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la modification du champagne / méthode update() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return false;
	}//end update() - champagne

	@Override
	public boolean delete(Integer pIdChampagne) {

		PreparedStatement ps = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, pIdChampagne);
			
			int verifSuppr = ps.executeUpdate();
			
			return (verifSuppr == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la suppression du champagne / méthode delete() ...");
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
	}//end delete() - champagne

	@Override
	public Champagne getById(Integer pIdChampagne) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, pIdChampagne);
			
			rs = ps.executeQuery();
			
			Champagne champagne = null;
			
			rs.next();
			
			int id = rs.getInt(1);
			String designation = rs.getString(2);
			String description = rs.getString(3);
			int prix = rs.getInt(4);
			int quantite = rs.getInt(5);
			boolean selectionne = rs.getBoolean(6);
			byte[] photo = rs.getBytes(7);
			double promo = rs.getDouble(8);
			int idType = rs.getInt(9);
			int idCepage = rs.getInt(10);
			int annee = rs.getInt(11);
			
			champagne = new Champagne(id, designation, description, prix, quantite, selectionne, photo, promo, idType, idCepage, annee);
			
			return champagne;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération du champagne / getById() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getById() - champagne

	@Override
	public Champagne getByNom(String pNomChampagne) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pNomChampagne);
			
			rs = ps.executeQuery();
			
			Champagne champagne = null;
			
			rs.next();
			
			int id = rs.getInt(1);
			String designation = rs.getString(2);
			String description = rs.getString(3);
			int prix = rs.getInt(4);
			int quantite = rs.getInt(5);
			boolean selectionne = rs.getBoolean(6);
			byte[] photo = rs.getBytes(7);
			double promo = rs.getDouble(8);
			int idType = rs.getInt(9);
			int idCepage = rs.getInt(10);
			int annee = rs.getInt(11);
			
			champagne = new Champagne(id, designation, description, prix, quantite, selectionne, photo, promo, idType, idCepage, annee);
			
			return champagne;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération du champagne / getByNom() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getByNom() - champagne

	@Override
	public List<Champagne> getAll() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			rs = ps.executeQuery();
			
			Champagne champagne = null;
			List<Champagne> listeChampagnes = new ArrayList<>();
			
			while (rs.next()) {
				
				champagne = new Champagne(rs.getInt(1),
							  			  rs.getString(2),
							  			  rs.getString(3),
							  			  rs.getInt(4),
							  			  rs.getInt(5),
							  			  rs.getBoolean(6),
							  			  rs.getBytes(7),
							  			  rs.getDouble(8),
							  			  rs.getInt(11),
							  			  rs.getInt(12),
							  			  rs.getInt(13));
				
				listeChampagnes.add(champagne);
				
			}//end while
			
			return listeChampagnes;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des champagnes / méthode getAll() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getall() - champagne

	@Override
	public List<Champagne> getByType(int idType) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, idType);
			
			rs = ps.executeQuery();
			
			Champagne champagne = null;
			List<Champagne> listeChampagnes = new ArrayList<>();
			
			while (rs.next()) {
				
				champagne = new Champagne(rs.getInt(1),
							  			  rs.getString(2),
							  			  rs.getString(3),
							  			  rs.getInt(4),
							  			  rs.getInt(5),
							  			  rs.getBoolean(6),
							  			  rs.getBytes(7),
							  			  rs.getDouble(8),
							  			  rs.getInt(11),
							  			  rs.getInt(12),
							  			  rs.getInt(13));
				
				listeChampagnes.add(champagne);
				
			}//end while
			
			return listeChampagnes;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des champagnes / méthode getByType() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getByType() - champagne

	@Override
	public List<Champagne> getByAnnee(Integer annee) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, annee);
			
			rs = ps.executeQuery();
			
			Champagne champagne = null;
			List<Champagne> listeChampagnes = new ArrayList<>();
			
			while (rs.next()) {
				
				champagne = new Champagne(rs.getInt(1),
							  			  rs.getString(2),
							  			  rs.getString(3),
							  			  rs.getInt(4),
							  			  rs.getInt(5),
							  			  rs.getBoolean(6),
							  			  rs.getBytes(7),
							  			  rs.getDouble(8),
							  			  rs.getInt(11),
							  			  rs.getInt(12),
							  			  rs.getInt(13));
				
				listeChampagnes.add(champagne);
				
			}//end while
			
			return listeChampagnes;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des champagnes / méthode getByAnnee() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getByAnnee() - champagne

	@Override
	public List<Champagne> getByCepage(int idCepage) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, idCepage);
			
			rs = ps.executeQuery();
			
			Champagne champagne = null;
			List<Champagne> listeChampagnes = new ArrayList<>();
			
			while (rs.next()) {
				
				champagne = new Champagne(rs.getInt(1),
							  			  rs.getString(2),
							  			  rs.getString(3),
							  			  rs.getInt(4),
							  			  rs.getInt(5),
							  			  rs.getBoolean(6),
							  			  rs.getBytes(7),
							  			  rs.getDouble(8),
							  			  rs.getInt(11),
							  			  rs.getInt(12),
							  			  rs.getInt(13));
				
				listeChampagnes.add(champagne);
				
			}//end while
			
			return listeChampagnes;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des champagnes / méthode getByCepage() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getByCepage() - champagne

}//end class
