package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.Spiritueux;

public class SpiritueuxDAOImpl implements ISpiritueuxDAO {

	@Override
	public boolean add(Spiritueux pSpiritueux) {

		PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pSpiritueux.getDesignation());
			ps.setString(2, pSpiritueux.getDescription());
			ps.setDouble(3, pSpiritueux.getPrix());
			ps.setInt(4, pSpiritueux.getQuantite());
			ps.setBytes(5, pSpiritueux.getPhoto());
			ps.setInt(6, pSpiritueux.getIdPays());
			ps.setInt(7, pSpiritueux.getIdType());
			
			int verifUpdate = ps.executeUpdate();
			
			return (verifUpdate == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de l'ajout du spiritueux / méthode update() ...");
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
	}//end add() - spiritueux

	@Override
	public boolean update(Spiritueux pSpiritueux) {

		PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pSpiritueux.getDesignation());
			ps.setString(2, pSpiritueux.getDescription());
			ps.setDouble(3, pSpiritueux.getPrix());
			ps.setInt(4, pSpiritueux.getQuantite());
			ps.setBytes(5, pSpiritueux.getPhoto());
			ps.setInt(6, pSpiritueux.getIdPays());
			ps.setInt(7, pSpiritueux.getIdType());
			ps.setInt(8, pSpiritueux.getIdProduit());
			
			int verifUpdate = ps.executeUpdate();
			
			return (verifUpdate == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la modification du spiritueux / méthode update() ...");
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
	}//end update() - spiritueux

	@Override
	public boolean delete(Integer pIdSpiritueux) {

		PreparedStatement ps = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, pIdSpiritueux);
			
			int verifSuppr = ps.executeUpdate();
			
			return (verifSuppr == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la suppression du spiritueux / méthode delete() ...");
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
	}//end delete() - spiritueux

	@Override
	public Spiritueux getById(Integer pIdSpiritueux) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, pIdSpiritueux);
			
			rs = ps.executeQuery();
			
			Spiritueux spiritueux = null;
			
			spiritueux = new Spiritueux(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getInt(4),
										rs.getInt(5),
										rs.getBoolean(6),
										rs.getBytes(7),
										rs.getDouble(8),
										rs.getInt(9),
										rs.getInt(10));
			
			return spiritueux;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération du spiritueux / méthode getById() ...");
		
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
	}//end getById() - spiritueux

	@Override
	public Spiritueux getByNom(String pNomSpiritueux) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pNomSpiritueux);
			
			rs = ps.executeQuery();
			
			Spiritueux spiritueux = null;
			
			spiritueux = new Spiritueux(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getInt(4),
										rs.getInt(5),
										rs.getBoolean(6),
										rs.getBytes(7),
										rs.getDouble(8),
										rs.getInt(9),
										rs.getInt(10));
			
			return spiritueux;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération du spiritueux / méthode getByNom() ...");
		
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
	}//end getByNom() - spiritueux

	@Override
	public List<Spiritueux> getAll() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			rs = ps.executeQuery();
			
			Spiritueux spiritueux = null;
			List<Spiritueux> listeSpiritueux = new ArrayList<>();
			
			while (rs.next()) {
				
				spiritueux = new Spiritueux(rs.getInt(1),
											rs.getString(2),
											rs.getString(3),
											rs.getInt(4),
											rs.getInt(5),
											rs.getBoolean(6),
											rs.getBytes(7),
											rs.getDouble(8),
											rs.getInt(9),
											rs.getInt(10));
				
				listeSpiritueux.add(spiritueux);
				
			}//end while
			
			return listeSpiritueux;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des spiritueux / méthode getAll() ...");
		
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
	}//end getAll() - spiritueux

	@Override
	public List<Spiritueux> getByPays(int idPays) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, idPays);
			
			rs = ps.executeQuery();
			
			Spiritueux spiritueux = null;
			List<Spiritueux> listeSpiritueux = new ArrayList<>();
			
			while (rs.next()) {
				
				spiritueux = new Spiritueux(rs.getInt(1),
											rs.getString(2),
											rs.getString(3),
											rs.getInt(4),
											rs.getInt(5),
											rs.getBoolean(6),
											rs.getBytes(7),
											rs.getDouble(8),
											rs.getInt(9),
											rs.getInt(10));
				
				listeSpiritueux.add(spiritueux);
				
			}//end while
			
			return listeSpiritueux;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des spiritueux / méthode getByPays() ...");
		
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
	}//end getByPays() - spiritueux

	@Override
	public List<Spiritueux> getByType(int idType) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, idType);
			
			rs = ps.executeQuery();
			
			Spiritueux spiritueux = null;
			List<Spiritueux> listeSpiritueux = new ArrayList<>();
			
			while (rs.next()) {
				
				spiritueux = new Spiritueux(rs.getInt(1),
											rs.getString(2),
											rs.getString(3),
											rs.getInt(4),
											rs.getInt(5),
											rs.getBoolean(6),
											rs.getBytes(7),
											rs.getDouble(8),
											rs.getInt(9),
											rs.getInt(10));
				
				listeSpiritueux.add(spiritueux);
				
			}//end while
			
			return listeSpiritueux;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des spiritueux / méthode getByType() ...");
		
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
	}//end getByType() - spiritueux

}//end class
