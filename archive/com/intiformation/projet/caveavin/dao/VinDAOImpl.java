package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.Vin;

public class VinDAOImpl implements IVinDAO {

	@Override
	public boolean add(Vin pVin) {

		PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pVin.getDesignation());
			ps.setString(2, pVin.getDescription());
			ps.setDouble(3, pVin.getPrix());
			ps.setInt(4, pVin.getQuantite());
			ps.setBytes(5, pVin.getPhoto());
			ps.setInt(6, pVin.getIdPays());
			ps.setInt(7, pVin.getIdRegion());
			ps.setInt(8, pVin.getIdType());
			ps.setInt(9, pVin.getIdCepage());
			ps.setInt(10, pVin.getAnnee());
			
			int verifUpdate = ps.executeUpdate();
			
			return (verifUpdate == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de l'ajout du vin / méthode add() ...");
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
	}//end add() - vin

	@Override
	public boolean update(Vin pVin) {

		PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pVin.getDesignation());
			ps.setString(2, pVin.getDescription());
			ps.setDouble(3, pVin.getPrix());
			ps.setInt(4, pVin.getQuantite());
			ps.setBytes(5, pVin.getPhoto());
			ps.setInt(6, pVin.getIdPays());
			ps.setInt(7, pVin.getIdRegion());
			ps.setInt(8, pVin.getIdType());
			ps.setInt(9, pVin.getIdCepage());
			ps.setInt(10, pVin.getAnnee());
			ps.setInt(11, pVin.getIdProduit());
			
			int verifUpdate = ps.executeUpdate();
			
			return (verifUpdate == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la modification du vin / méthode update() ...");
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
	}//end update() - vin

	@Override
	public boolean delete(Integer pIdVin) {

		PreparedStatement ps = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, pIdVin);
			
			int verifSuppr = ps.executeUpdate();
			
			return (verifSuppr == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la suppression du vin / méthode delete() ...");
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
	}//end delete() - vin

	@Override
	public Vin getById(Integer pIdVin) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, pIdVin);
			
			rs = ps.executeQuery();
			
			Vin vin = null;
			
			rs.next();
			
			int id = rs.getInt(1);
			String designation = rs.getString(2);
			String description = rs.getString(3);
			int prix = rs.getInt(4);
			int quantite = rs.getInt(5);
			boolean selectionne = rs.getBoolean(6);
			byte[] photo = rs.getBytes(7);
			double promo = rs.getDouble(8);
			int idPays = rs.getInt(9);
			int idRegion = rs.getInt(10);
			int idType = rs.getInt(11);
			int idCepage = rs.getInt(12);
			int annee = rs.getInt(13);
			
			vin = new Vin(id, designation, description, prix, quantite, selectionne, photo, promo, idPays, idRegion, idType, idCepage, annee);
			
			return vin;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération du vin / getById() ...");
		
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
	}//end getById() - vin

	@Override
	public Vin getByNom(String pNomVin) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pNomVin);
			
			rs = ps.executeQuery();
			
			Vin vin = null;
			
			rs.next();
			
			int id = rs.getInt(1);
			String designation = rs.getString(2);
			String description = rs.getString(3);
			int prix = rs.getInt(4);
			int quantite = rs.getInt(5);
			boolean selectionne = rs.getBoolean(6);
			byte[] photo = rs.getBytes(7);
			double promo = rs.getDouble(8);
			int idPays = rs.getInt(9);
			int idRegion = rs.getInt(10);
			int idType = rs.getInt(11);
			int idCepage = rs.getInt(12);
			int annee = rs.getInt(13);
			
			vin = new Vin(id, designation, description, prix, quantite, selectionne, photo, promo, idPays, idRegion, idType, idCepage, annee);
			
			return vin;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération du vin - getByNom() ...");
		
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
	}//end getByNom() - vin

	@Override
	public List<Vin> getAll() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			rs = ps.executeQuery();
			
			Vin vin = null;
			List<Vin> listeVins = new ArrayList<>();
			
			while (rs.next()) {
				
				vin = new Vin(rs.getInt(1),
							  rs.getString(2),
							  rs.getString(3),
							  rs.getInt(4),
							  rs.getInt(5),
							  rs.getBoolean(6),
							  rs.getBytes(7),
							  rs.getDouble(8),
							  rs.getInt(9),
							  rs.getInt(10),
							  rs.getInt(11),
							  rs.getInt(12),
							  rs.getInt(13));
				
				listeVins.add(vin);
				
			}//end while
			
			return listeVins;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des vins / méthode getAll() ...");
		
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
	}//end getAll() - vin

	@Override
	public List<Vin> getByPays(int idPays) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, idPays);
			
			rs = ps.executeQuery();
			
			Vin vin = null;
			List<Vin> listeVins = new ArrayList<>();
			
			while (rs.next()) {
				
				vin = new Vin(rs.getInt(1),
							  rs.getString(2),
							  rs.getString(3),
							  rs.getInt(4),
							  rs.getInt(5),
							  rs.getBoolean(6),
							  rs.getBytes(7),
							  rs.getDouble(8),
							  rs.getInt(9),
							  rs.getInt(10),
							  rs.getInt(11),
							  rs.getInt(12),
							  rs.getInt(13));
				
				listeVins.add(vin);
				
			}//end while
			
			return listeVins;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des vins / méthode getByPays() ...");
		
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
	}//end getByPays() - vin

	@Override
	public List<Vin> getByRegion(int idRegion) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, idRegion);
			
			rs = ps.executeQuery();
			
			Vin vin = null;
			List<Vin> listeVins = new ArrayList<>();
			
			while (rs.next()) {
				
				vin = new Vin(rs.getInt(1),
							  rs.getString(2),
							  rs.getString(3),
							  rs.getInt(4),
							  rs.getInt(5),
							  rs.getBoolean(6),
							  rs.getBytes(7),
							  rs.getDouble(8),
							  rs.getInt(9),
							  rs.getInt(10),
							  rs.getInt(11),
							  rs.getInt(12),
							  rs.getInt(13));
				
				listeVins.add(vin);
				
			}//end while
			
			return listeVins;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des vins / méthode getByRegion() ...");
		
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
	}//end getByRegion() - vin

	@Override
	public List<Vin> getByType(int idType) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, idType);
			
			rs = ps.executeQuery();
			
			Vin vin = null;
			List<Vin> listeVins = new ArrayList<>();
			
			while (rs.next()) {
				
				vin = new Vin(rs.getInt(1),
							  rs.getString(2),
							  rs.getString(3),
							  rs.getInt(4),
							  rs.getInt(5),
							  rs.getBoolean(6),
							  rs.getBytes(7),
							  rs.getDouble(8),
							  rs.getInt(9),
							  rs.getInt(10),
							  rs.getInt(11),
							  rs.getInt(12),
							  rs.getInt(13));
				
				listeVins.add(vin);
				
			}//end while
			
			return listeVins;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des vins / méthode getByType() ...");
		
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
	}//end getByType() - vin

	@Override
	public List<Vin> getByAnnee(Integer pAnnee) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, pAnnee);
			
			rs = ps.executeQuery();
			
			Vin vin = null;
			List<Vin> listeVins = new ArrayList<>();
			
			while (rs.next()) {
				
				vin = new Vin(rs.getInt(1),
							  rs.getString(2),
							  rs.getString(3),
							  rs.getInt(4),
							  rs.getInt(5),
							  rs.getBoolean(6),
							  rs.getBytes(7),
							  rs.getDouble(8),
							  rs.getInt(9),
							  rs.getInt(10),
							  rs.getInt(11),
							  rs.getInt(12),
							  rs.getInt(13));
				
				listeVins.add(vin);
				
			}//end while
			
			return listeVins;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des vins / méthode getByAnnee() ...");
		
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
	}//end getByAnnee() - vin

	@Override
	public List<Vin> getByCepage(int idCepage) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, idCepage);
			
			rs = ps.executeQuery();
			
			Vin vin = null;
			List<Vin> listeVins = new ArrayList<>();
			
			while (rs.next()) {
				
				vin = new Vin(rs.getInt(1),
							  rs.getString(2),
							  rs.getString(3),
							  rs.getInt(4),
							  rs.getInt(5),
							  rs.getBoolean(6),
							  rs.getBytes(7),
							  rs.getDouble(8),
							  rs.getInt(9),
							  rs.getInt(10),
							  rs.getInt(11),
							  rs.getInt(12),
							  rs.getInt(13));
				
				listeVins.add(vin);
				
			}//end while
			
			return listeVins;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des vins / méthode getByCepage() ...");
		
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
	}//and getByCepage() - vin

}//end class
